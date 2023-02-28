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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReconciliationService {
  private static final String APIKEY_DIRECTDEBIT = "YRj9URqFn39XrDRgil9lwozjQ4CoJa";

  private final PaymentMethodRepository paymentMethodRepository;
  private final PaymentReconciliationRepository paymentReconciliationRepository;
  private final PaymentRepository paymentRepository;

  private final IxopayClient ixopayClient;

  @Value("${ixopay.direct-debit}")
  private String directDebitApiKey;
  @Transactional
  public void migrateOneDebit() {
    final PaymentReconciliation paymentReconciliation =
        paymentReconciliationRepository.find1ByType(PaymentType.DEBIT).get(0);
    migrate(paymentReconciliation);
  }

  @Transactional
  public void migrateById(final String id) {
    final PaymentReconciliation paymentReconciliation =
        paymentReconciliationRepository.findById(id).orElseThrow();
    migrate(paymentReconciliation);
  }

  private void migrate(final PaymentReconciliation paymentReconciliation){
    final TransactionResponse response = ixopayClient.retrieveStatusByMerchantTransactionId(
        directDebitApiKey, paymentReconciliation.getId());

    final PaymentMethod paymentMethod = paymentMethodRepository
        .findByGatewayReferenceId(response.getReferenceUuid()).orElseThrow();

    final Payment payment = Payment.builder()
        .id(paymentReconciliation.getId())
        .externalPaymentId(paymentReconciliation.getExternalPaymentId())
        .accountId(paymentReconciliation.getAccountId())
        .paymentMethod(paymentMethod)
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
        .build();

    paymentRepository.save(payment);
  }
}
