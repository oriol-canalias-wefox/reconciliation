package com.wefox.finops.reconciliation.repository;

import com.wefox.finops.reconciliation.domain.Payment;
import com.wefox.finops.reconciliation.domain.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, String> {

}
