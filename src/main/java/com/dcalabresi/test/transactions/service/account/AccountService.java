package com.dcalabresi.test.transactions.service.account;

import com.dcalabresi.test.transactions.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by damian on 9/16/16.
 */
@Service
public class AccountService {

    private ConcurrentMap<Integer, Account> accounts;

    public AccountService() {
        accounts = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 3 ; i++) {
            Account account = AccountGenerator.getRandomAccount();
            accounts.put(account.getId(), account);
        }
    }

    public Account getAccount(Integer accountId) {
        return Optional.ofNullable(accounts.get(accountId))
                .orElseThrow(() -> new RuntimeException("The account id doesn't exist"));
    }

    public Collection<Account> getAccounts() {
        return accounts.values();
    }
}
