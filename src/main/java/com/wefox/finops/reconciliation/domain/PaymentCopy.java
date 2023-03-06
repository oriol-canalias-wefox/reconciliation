package com.wefox.finops.reconciliation.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PaymentCopy {

  @Id
  private String id;

  private String externalPaymentId;

  private String accountId;

  @ManyToOne(fetch = FetchType.LAZY)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  private PaymentType type;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  private BigDecimal amount;

  private String currency;

  private String gatewayReferenceId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "debitPaymentId")
  private Payment debitPayment;

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
}
