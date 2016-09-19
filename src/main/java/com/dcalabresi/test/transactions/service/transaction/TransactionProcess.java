package com.dcalabresi.test.transactions.service.transaction;

import com.dcalabresi.test.transactions.entity.Account;
import com.dcalabresi.test.transactions.exception.AmountNotEnoughException;
import com.dcalabresi.test.transactions.service.account.AccountService;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by damian on 9/17/16.
 */
public class TransactionProcess implements Runnable {

    private static AtomicInteger TRANSACTION_COUNTER = new AtomicInteger(0);
    private static Lock ARBITRATOR = new ReentrantLock();

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
        runWithTryCatch(
                aVoid1 -> TransactionLogger.INSTANCE.logStart(transactionId),
                () -> runTransaction(),
                message -> TransactionLogger.INSTANCE.logSuccess(transactionId, message),
                ex -> TransactionLogger.INSTANCE.logError(transactionId, ex)
        );
    }

    private void runWithTryCatch(Consumer<Void> doFirst, Supplier<String> doInside, Consumer<String> doIfSuccess,
                                 Consumer<Exception> doIfFail) {
        doFirst.accept(null);
        try {
            String message = doInside.get();
            doIfSuccess.accept(message);
        } catch (Exception ex) {
            doIfFail.accept(ex);
        }
    }

    private String runTransaction() {
        Account origAccount = accountService.getAccount(origId);
        Account destAccount = accountService.getAccount(destId);
        return runSynchronized(origAccount, destAccount, () -> {
            new Transfer(origAccount, destAccount, amount).makeTransfer();
            return "From account " + origAccount.getId()
                    + " (" + origAccount.getBalance() + ") To account " + destAccount.getId()
                    + "(" + destAccount.getBalance() + ")";
        });
    }

    private String runSynchronized(Object firstObject, Object secondObject, Supplier<String> aConsumer) {
        // Locking before taking both account will prevent a deadlock if another thread is trying to take the second account.
        String message;
        ARBITRATOR.lock();
        synchronized (firstObject) {
            synchronized (secondObject) {
                ARBITRATOR.unlock();
                message = aConsumer.get();
            }
        }
        return message;
    }

}
