package com.wefox.finops.reconciliation.repository;

import com.wefox.finops.reconciliation.domain.PaymentCopy;
import org.springframework.data.repository.CrudRepository;

public interface PaymentCopyRepository extends CrudRepository<PaymentCopy, String> {

}
