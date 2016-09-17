package com.dcalabresi.test.transactions.service.transaction;

import com.dcalabresi.test.transactions.service.account.AccountService;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by damian on 9/17/16.
 */
public class TransactionProcess implements Runnable {

    private static AtomicInteger TRANSACTION_COUNTER = new AtomicInteger(0);

    private Integer transactionId;
    private AccountService accountService;
    private Integer origId;
    private Integer destId;
    private BigDecimal amount;

    public TransactionProcess(AccountService accountService, Integer origId, Integer destId, BigDecimal amount) {
        this.transactionId = TRANSACTION_COUNTER.incrementAndGet();
        this.accountService = accountService;
        this.origId = origId;
        this.destId = destId;
        this.amount = amount;
    }

    @Override
    public void run() {
        System.out.println("Start running transaction: " + transactionId
                + " - origId: " + origId
                + " - destId: " + destId
                + " - amount: " + amount);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish transaction: "+ transactionId);
    }

}
