package com.wefox.finops.reconciliation.repository;

import com.wefox.finops.reconciliation.domain.PaymentMethod;

import java.util.List;
import java.util.Optional;

import com.wefox.finops.reconciliation.domain.PaymentReconciliation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, String> {

  Optional<PaymentMethod> findByGatewayReferenceId(String referenceUuid);

  @Query("select p from PaymentMethod p where p.accountId = :accountId and p.accountDefault = true and p.active = true")
  List<PaymentMethod> findByAccountDefault(String accountId);
}
