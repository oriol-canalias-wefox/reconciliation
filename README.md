# Reconciliation


### Database

Table for temp data:
```sql 
create table finance.payment_reconciliation
(
    id                            varchar(255)   not null primary key,
    amount                        numeric(19, 2) ,
    created_date                  timestamp(6),
    currency                      varchar(255)   ,
    description                   varchar(255),
    error_code                    varchar(255),
    error_message                 varchar(1000),
    external_payment_id           varchar(255),
    gateway_reference_id          varchar(255),
    modified_date                 timestamp(6),
    status                        varchar(255)   ,
    type                          varchar(255)   ,
    account_id                    varchar(255),
    debit_payment_id              varchar(255),
    payment_method_id             varchar(255),
    chargeback_transaction_id     varchar(255),
    qr_code                       varchar(15000),
    additional_data               varchar(255),
    chargeback_transaction_reason varchar(255),
    execution_date                timestamp(6),
    issuing_date                  timestamp(6),
    country_code                  varchar(8),
    policy_number                 varchar(64),
    migrate                       boolean
);

```