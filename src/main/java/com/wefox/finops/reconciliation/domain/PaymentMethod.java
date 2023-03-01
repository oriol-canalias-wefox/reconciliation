package com.wefox.finops.reconciliation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethod {

  @Id
  private String id;

  private String paymentToken;

  private String gatewayReferenceId;

  private String accountId;

  private Boolean accountDefault;

  private Boolean active;

}
