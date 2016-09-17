package com.dcalabresi.test.transactions.service.transaction;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by damian on 9/17/16.
 */
@Service
public class TransactionService {

    private Queue<TransactionRequest> transactionsPending;

    public TransactionService() {
        this.transactionsPending = new LinkedBlockingQueue<>();
    }

    public void addTransaction(Integer origAccountId, Integer destAccountId, BigDecimal amount) {
        TransactionRequest transactionRequest = new TransactionRequest(origAccountId, destAccountId, amount);
        transactionsPending.add(transactionRequest);
    }


}
