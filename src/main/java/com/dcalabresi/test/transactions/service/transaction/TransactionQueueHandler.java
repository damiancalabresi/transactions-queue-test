package com.dcalabresi.test.transactions.service.transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        executorService.submit(transaction);
    }


}
