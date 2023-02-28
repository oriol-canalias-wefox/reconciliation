package com.wefox.finops.reconciliation.client;
import com.wefox.finops.reconciliation.domain.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ixopayClient", url = "https://wefox.ixopay.com/api/v3/")
public interface IxopayClient {
    @GetMapping("status/{apiKey}/getByMerchantTransactionId/{merchantTransactionId}")
    TransactionResponse register(
            @PathVariable("apiKey") String apiKey, @PathVariable("merchantTransactionId")  String merchantTransactionId);
}
