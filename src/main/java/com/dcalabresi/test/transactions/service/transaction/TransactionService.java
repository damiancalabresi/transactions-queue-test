package com.dcalabresi.test.transactions.service.transaction;

import com.dcalabresi.test.transactions.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by damian on 9/17/16.
 */
@Service
public class TransactionService {

    @Autowired
    AccountService accountService;

    public TransactionService() {
    }

    public void addTransaction(Integer origAccountId, Integer destAccountId, BigDecimal amount) {
        TransactionProcess transactionProcess = new TransactionProcess(accountService, origAccountId,
                destAccountId, amount);
        TransactionQueueHandler.INSTANCE.queueTransaction(transactionProcess);
    }


}
