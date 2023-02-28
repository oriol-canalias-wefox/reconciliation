package com.wefox.finops.reconciliation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PaymentReconciliation {

  @Id
  private String id;

  private String externalPaymentId;

  private String accountId;

  @Enumerated(EnumType.STRING)
  private PaymentType type;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  private BigDecimal amount;

  private String currency;

  private String gatewayReferenceId;

  private String description;

  private String qrCode;

  private String errorCode;

  private String errorMessage;

  private String chargebackTransactionId;

  private String chargebackTransactionReason;

  private Instant createdDate;

  private Instant modifiedDate;

  private Instant executionDate;

  private Instant issuingDate;

  private String countryCode;

  private String policyNumber;

  private Boolean migrate;
}
