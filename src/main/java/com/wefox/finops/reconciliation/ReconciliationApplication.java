package com.wefox.finops.reconciliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class ReconciliationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReconciliationApplication.class, args);
	}

}
