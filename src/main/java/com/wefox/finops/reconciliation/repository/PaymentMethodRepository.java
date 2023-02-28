package com.wefox.finops.reconciliation.repository;

import com.wefox.finops.reconciliation.domain.PaymentMethod;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, String> {

  Optional<PaymentMethod> findByGatewayReferenceId(String referenceUuid);
}
