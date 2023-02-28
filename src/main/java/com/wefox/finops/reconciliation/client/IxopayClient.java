package com.wefox.finops.reconciliation.client;
import com.wefox.finops.reconciliation.domain.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ixopayClient", url = "${ixopay.url}")
public interface IxopayClient {
    @GetMapping("status/{apiKey}/getByMerchantTransactionId/{merchantTransactionId}")
    TransactionResponse retrieveStatusByMerchantTransactionId(
            @PathVariable("apiKey") String apiKey, @PathVariable("merchantTransactionId")  String merchantTransactionId);
}
