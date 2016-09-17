package com.dcalabresi.test.transactions.service.transaction;

import java.math.BigDecimal;

/**
 * Created by damian on 9/17/16.
 */
public class TransactionRequest {
    private Integer origId;
    private Integer destId;
    private BigDecimal amount;

    public TransactionRequest(Integer origId, Integer destId, BigDecimal amount) {
        this.origId = origId;
        this.destId = destId;
        this.amount = amount;
    }

    public Integer getOrigId() {
        return origId;
    }

    public Integer getDestId() {
        return destId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
