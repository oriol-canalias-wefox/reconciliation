package com.wefox.finops.reconciliation.repository;

import com.wefox.finops.reconciliation.domain.Payment;
import com.wefox.finops.reconciliation.domain.PaymentReconciliation;
import com.wefox.finops.reconciliation.domain.PaymentType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaymentReconciliationRepository extends
    JpaRepository<PaymentReconciliation, String> {

  @Query("select p from PaymentReconciliation p where (p.migrate = null or p.migrate = false) "
   + " and p.type = :type ")
  List<PaymentReconciliation> findByType(PaymentType type);

  @Query("select p from PaymentReconciliation p where p.migrate = null or p.migrate = false")
  List<PaymentReconciliation> findNotMigrated(Pageable pageable);
}
