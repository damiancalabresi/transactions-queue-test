package com.dcalabresi.test.transactions.service.account;

import com.dcalabresi.test.transactions.entity.Account;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by damian on 9/16/16.
 */
public class AccountGenerator {

    private static AtomicInteger accountId = new AtomicInteger(0);

    private static Random random = new Random();

    private static String[] banks = new String[]{"US Bank", "CitiBank"};

    private static String[] countries = new String[]{"US", "AR"};

    public static Account getRandomAccount() {
        Integer account = accountId.incrementAndGet();
        String bank = banks[random.nextInt(banks.length)];
        String country = countries[random.nextInt(countries.length)];
        BigDecimal balance = randomInitBalance();
        return new Account(account, bank, country, balance);
    }

    private static BigDecimal randomInitBalance() {
        long randomLong = Math.abs(new Random().nextLong() % 1000000);
        return BigDecimal.valueOf(randomLong, 2);
    }
}
