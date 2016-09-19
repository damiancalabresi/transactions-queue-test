package com.dcalabresi.test.transactions.service.transaction;

import java.math.BigDecimal;

/**
 * Created by damian on 9/18/16.
 */
public enum TransactionLogger {
    INSTANCE;

    TransactionLogger() {
    }

    public void logStart(Integer transactionId) {
        System.out.println("Transaction " + transactionId + " starting");
    }

    public void logError(Integer transactionId, Exception ex) {
        System.out.println("Transaction " + transactionId
                + " finished with errors. Exception: "
                + ex.getClass().getSimpleName()
                + " - Message: " + ex.getMessage());
    }

    public void logSuccess(Integer transactionId, Integer origAccountId, Integer destAccountId,
                           BigDecimal origBalance, BigDecimal destBalance) {
        System.out.println("Transaction " + transactionId
                + " finished with success. From account " + origAccountId
                + " (" + origBalance
                + ") To account " + destAccountId
                + " (" + destBalance + ")");
    }
}
