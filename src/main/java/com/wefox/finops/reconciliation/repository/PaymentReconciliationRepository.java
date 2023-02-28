package com.wefox.finops.reconciliation.repository;

import com.wefox.finops.reconciliation.domain.Payment;
import com.wefox.finops.reconciliation.domain.PaymentReconciliation;
import com.wefox.finops.reconciliation.domain.PaymentType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaymentReconciliationRepository extends CrudRepository<PaymentReconciliation, String> {

  @Query("select PaymentReconciliation from PaymentReconciliation p where (p.migrate = null or p.migrate = false) "
   + " and p.type = :type ")
  List<PaymentReconciliation> find1ByType(PaymentType type);
}
