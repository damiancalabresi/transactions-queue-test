package com.dcalabresi.test.transactions.exception;

import java.math.BigDecimal;

/**
 * Created by damian on 9/17/16.
 */
public class AmountNotEnoughException extends ManagedException {
    public AmountNotEnoughException(Integer accountId, BigDecimal accountBalance, BigDecimal amount) {
        super("The account: " + accountId + " has: " + accountBalance + " and needs: " + amount);
    }
}
