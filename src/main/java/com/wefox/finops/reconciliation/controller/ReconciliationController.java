package com.wefox.finops.reconciliation.controller;

import com.wefox.finops.reconciliation.service.ReconciliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reconciliation")
@RequiredArgsConstructor
public class ReconciliationController {
  private final ReconciliationService reconciliationService;

  @PostMapping("{id}")
  public void migrateById(@PathVariable String id) {
    reconciliationService.migrateById(id);
  }

  @PostMapping("/debits")
  public void migrateDebits() { reconciliationService.migrateAllDebit();}

  @PostMapping()
  public void migrateAll() { reconciliationService.migrateAll();}
}
