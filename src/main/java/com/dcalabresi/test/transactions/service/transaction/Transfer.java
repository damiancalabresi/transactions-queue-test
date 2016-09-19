package com.dcalabresi.test.transactions.service.transaction;

import com.dcalabresi.test.transactions.entity.Account;
import com.dcalabresi.test.transactions.exception.AmountNotEnoughException;

import java.math.BigDecimal;

/**
 * Created by damian on 9/19/16.
 */
public class Transfer {

    private Account origAccount;
    private Account destAccount;
    private BigDecimal amount;

    public Transfer(Account origAccount, Account destAccount, BigDecimal amount) {
        this.origAccount = origAccount;
        this.destAccount = destAccount;
        this.amount = amount;
    }

    public void makeTransfer() {
        BigDecimal tax = calculateTax(origAccount, destAccount, amount);
        checkAmountEnough(origAccount, amount.add(tax));
        origAccount.setBalance(origAccount.getBalance().subtract(amount).subtract(tax));
        destAccount.setBalance(destAccount.getBalance().add(amount));
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
