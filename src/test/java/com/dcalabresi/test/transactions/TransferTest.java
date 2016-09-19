package com.dcalabresi.test.transactions;

import com.dcalabresi.test.transactions.entity.Account;
import com.dcalabresi.test.transactions.service.transaction.Transfer;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by damian on 9/19/16.
 */
public class TransferTest {

    @Test
    public void testDecidePercentageDifferentCountry() {
        Transfer transfer = new Transfer(getArgentinianSantanderAccount(), getUSAAccount(), BigDecimal.TEN);
        Assert.assertEquals(new BigDecimal(5), transfer.decidePercentage());
    }

    @Test
    public void testDecidePercentageSameCountryDifferentBank() {
        Transfer transfer = new Transfer(getArgentinianSantanderAccount(), getArgentinianCitiBankAccount(), BigDecimal.TEN);
        Assert.assertEquals(new BigDecimal(1), transfer.decidePercentage());
    }

    @Test
    public void testDecidePercentageSameCountrySameBank() {
        Transfer transfer = new Transfer(getArgentinianSantanderAccount(), getArgentinianSantanderAccountAnother(), BigDecimal.TEN);
        Assert.assertEquals(new BigDecimal(0), transfer.decidePercentage());
    }

    @Test
    public void testDecidePercentageSameAccount() {
        Transfer transfer = new Transfer(getUSAAccount(), getUSAAccount(), BigDecimal.TEN);
        Assert.assertEquals(new BigDecimal(0), transfer.decidePercentage());
    }

    private Account getArgentinianCitiBankAccount() {
        return new Account(1, "CitiBank", "AR", BigDecimal.TEN);
    }

    private Account getUSAAccount() {
        return new Account(2, "CitiBank", "US", BigDecimal.TEN);
    }

    private Account getArgentinianSantanderAccount() {
        return new Account(3, "Santander", "AR", BigDecimal.TEN);
    }

    private Account getArgentinianSantanderAccountAnother() {
        return new Account(4, "Santander", "AR", BigDecimal.TEN);
    }

}
