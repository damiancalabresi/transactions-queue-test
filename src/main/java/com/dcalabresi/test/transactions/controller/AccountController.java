package com.dcalabresi.test.transactions.controller;

import com.dcalabresi.test.transactions.dto.AccountDto;
import com.dcalabresi.test.transactions.entity.Account;
import com.dcalabresi.test.transactions.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by damian on 9/16/16.
 */

@RestController
@RequestMapping("/api/account/")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public AccountDto getAccount(@PathVariable Integer id) {
        Account account = accountService.getAccount(id);
        return getAccountDto(account);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Collection<AccountDto> getAccounts() {
        return accountService.getAccounts().stream()
                .map(account -> getAccountDto(account))
                .collect(Collectors.toList());
    }

    private AccountDto getAccountDto(Account account) {
        return new AccountDto(account.getId(), account.getBank(), account.getCountry(), account.getBalance());
    }
}
