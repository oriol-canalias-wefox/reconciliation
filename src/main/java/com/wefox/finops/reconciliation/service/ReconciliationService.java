package com.wefox.finops.reconciliation.service;

import com.wefox.finops.reconciliation.client.IxopayClient;
import com.wefox.finops.reconciliation.domain.Payment;
import com.wefox.finops.reconciliation.domain.PaymentMethod;
import com.wefox.finops.reconciliation.domain.PaymentReconciliation;
import com.wefox.finops.reconciliation.domain.PaymentType;
import com.wefox.finops.reconciliation.domain.TransactionResponse;
import com.wefox.finops.reconciliation.repository.PaymentMethodRepository;
import com.wefox.finops.reconciliation.repository.PaymentReconciliationRepository;
import com.wefox.finops.reconciliation.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReconciliationService {
  private static final String APIKEY_DIRECTDEBIT = "YRj9URqFn39XrDRgil9lwozjQ4CoJa";

  private final PaymentMethodRepository paymentMethodRepository;
  private final PaymentReconciliationRepository paymentReconciliationRepository;
  private final PaymentRepository paymentRepository;

  private final IxopayClient ixopayClient;

  @Value("${ixopay.direct-debit}")
  private String directDebitApiKey;
  @Transactional
  public void migrateAllDebit() {
    final List<PaymentReconciliation> paymentReconciliation =
        paymentReconciliationRepository.findByType(PaymentType.DEBIT);
    if(paymentReconciliation.isEmpty()) throw new NotFoundException("Not found");
    migrateList(paymentReconciliation);
  }

  @Transactional
  public void migrateById(final String id) {
    final PaymentReconciliation paymentReconciliation =
        paymentReconciliationRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    migrate(paymentReconciliation);
  }

  @Transactional
  public void migrateAll() {
    final List<PaymentReconciliation> paymentReconciliation =
            paymentReconciliationRepository.findNotMigrated();
    if(paymentReconciliation.isEmpty()) throw new NotFoundException("Not found");
    migrateList(paymentReconciliation);
  }

  private void migrateList(final List<PaymentReconciliation> paymentReconciliations){
      for(PaymentReconciliation paymentReconciliation : paymentReconciliations) {
          migrate(paymentReconciliation);
      }
  }

  private void migrate(final PaymentReconciliation paymentReconciliation){
    PaymentMethod paymentMethod;
    if(paymentReconciliation.getType() == PaymentType.DEBIT){
      final TransactionResponse response = ixopayClient.retrieveStatusByMerchantTransactionId(
              directDebitApiKey, paymentReconciliation.getId());
      paymentMethod = paymentMethodRepository
              .findByGatewayReferenceId(response.getReferenceUuid()).orElseThrow();
    }else{
      List<PaymentMethod> pds = paymentMethodRepository.findByAccountDefault(paymentReconciliation.getAccountId());
      paymentMethod = pds.get(0);
    }

    final Payment payment = Payment.builder()
            .id(paymentReconciliation.getId())
            .externalPaymentId(paymentReconciliation.getExternalPaymentId())
            .accountId(paymentReconciliation.getAccountId())
            .type(paymentReconciliation.getType())
            .status(paymentReconciliation.getStatus())
            .amount(paymentReconciliation.getAmount())
            .currency(paymentReconciliation.getCurrency())
            .gatewayReferenceId(paymentReconciliation.getGatewayReferenceId())
            .description(paymentReconciliation.getDescription())
            .qrCode(paymentReconciliation.getQrCode())
            .errorCode(paymentReconciliation.getErrorCode())
            .errorMessage(paymentReconciliation.getErrorMessage())
            .createdDate(paymentReconciliation.getCreatedDate())
            .modifiedDate(paymentReconciliation.getModifiedDate())
            .executionDate(paymentReconciliation.getExecutionDate())
            .issuingDate(paymentReconciliation.getIssuingDate())
            .countryCode("EUR".equals(paymentReconciliation.getCurrency()) ? "DE" : "CH")
            .paymentMethod(paymentMethod)
            .build();
    paymentRepository.save(payment);

    paymentReconciliation.setMigrate(true);
    paymentReconciliationRepository.save(paymentReconciliation);

  }
}
