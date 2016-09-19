package com.dcalabresi.test.transactions.service.transaction;

import com.dcalabresi.test.transactions.entity.Account;
import com.dcalabresi.test.transactions.exception.AmountNotEnoughException;
import com.dcalabresi.test.transactions.service.account.AccountService;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

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
                aVoid2 -> {
                    Account origAccount = accountService.getAccount(origId);
                    Account destAccount = accountService.getAccount(destId);
                    runSynchronized(origAccount, destAccount, aVoid -> {
                        BigDecimal tax = calculateTax(origAccount, destAccount, amount);
                        checkAmountEnough(origAccount, amount.add(tax));
                        origAccount.setBalance(origAccount.getBalance().subtract(amount).subtract(tax));
                        destAccount.setBalance(destAccount.getBalance().add(amount));
                        TransactionLogger.INSTANCE.logSuccess(transactionId, origId, destId,
                                origAccount.getBalance(), destAccount.getBalance());
                    });
                },
                ex -> TransactionLogger.INSTANCE.logError(transactionId, ex));
    }

    private void runWithTryCatch(Consumer<Void> doFirst, Consumer<Void> doInside, Consumer<Exception> doIfFail) {
        doFirst.accept(null);
        try {
            doInside.accept(null);
        } catch (Exception ex) {
            doIfFail.accept(ex);
        }
    }

    private void runSynchronized(Object firstObject, Object secondObject, Consumer<Void> aConsumer) {
        // Locking before taking both account will prevent a deadlock if another thread is trying to take the second account.
        ARBITRATOR.lock();
        synchronized (firstObject) {
            synchronized (secondObject) {
                ARBITRATOR.unlock();
                aConsumer.accept(null);
            }
        }
    }

    private void checkAmountEnough(Account account, BigDecimal amount) {
        if(account.getBalance().compareTo(amount)<0)
            throw new AmountNotEnoughException(account.getId(), account.getBalance(), amount);
    }

    private BigDecimal calculateTax(Account origAccount, Account destAccount, BigDecimal amount) {
        BigDecimal percentage = decidePercentage(origAccount, destAccount);
        return amount.multiply(percentage).divide(new BigDecimal(100));
    }

    private BigDecimal decidePercentage(Account origAccount, Account destAccount) {
        if(origAccount.getCountry().equals(destAccount.getCountry())) {
            if(origAccount.getBank().equals(destAccount.getBank())) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.ONE;
            }
        } else {
            return new BigDecimal(5);
        }
    }

}
