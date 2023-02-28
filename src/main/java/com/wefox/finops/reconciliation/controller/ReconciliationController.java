package com.wefox.finops.reconciliation.controller;

import com.wefox.finops.reconciliation.service.ReconciliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reconciliation")
@RequiredArgsConstructor
public class ReconciliationController {
  private final ReconciliationService reconciliationService;

  @PostMapping("{id}")
  public void migrateById(@PathVariable String id) {
    reconciliationService.migrateById(id);
  }
}
