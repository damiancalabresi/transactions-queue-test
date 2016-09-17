package com.dcalabresi.test.transactions.service.transaction;

import com.dcalabresi.test.transactions.exception.TransactionRejectedException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Created by damian on 9/17/16.
 */
public enum TransactionQueueHandler {
    INSTANCE;

    private ExecutorService executorService;

    TransactionQueueHandler() {
        this.executorService = Executors.newWorkStealingPool();
    }

    public void queueTransaction(TransactionProcess transaction) {
        try {
            executorService.submit(transaction);
        } catch (RejectedExecutionException ex) {
            throw new TransactionRejectedException();
        }
    }


}
