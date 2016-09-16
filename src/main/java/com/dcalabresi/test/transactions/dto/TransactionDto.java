package com.dcalabresi.test.transactions.dto;

import java.math.BigDecimal;

/**
 * Created by damian on 9/16/16.
 */
public class TransactionDto {

    private Integer origId;
    private Integer destId;
    private BigDecimal amount;

    public TransactionDto() {
    }

    public TransactionDto(Integer origId, Integer destId, BigDecimal amount) {
        this.origId = origId;
        this.destId = destId;
        this.amount = amount;
    }

    public Integer getOrigId() {
        return origId;
    }

    public void setOrigId(Integer origId) {
        this.origId = origId;
    }

    public Integer getDestId() {
        return destId;
    }

    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
