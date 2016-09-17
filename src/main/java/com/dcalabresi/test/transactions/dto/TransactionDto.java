package com.dcalabresi.test.transactions.dto;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Created by damian on 9/16/16.
 */
public class TransactionDto {

    @Min(value = 1, message = "The account id should be greater than zero")
    private Integer origId;

    @Min(value = 1, message = "The account id should be greater than zero")
    private Integer destId;

    @Min(value = 0, message = "The amount to transfer should be greater than zero")
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
