package com.dcalabresi.test.transactions.dto;

import java.math.BigDecimal;

/**
 * Created by damian on 9/16/16.
 */
public class AccountDto {

    private Integer id;
    private String bank;
    private String country;
    private BigDecimal balance;

    public AccountDto(Integer id, String bank, String country, BigDecimal balance) {
        this.id = id;
        this.bank = bank;
        this.country = country;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public String getBank() {
        return bank;
    }

    public String getCountry() {
        return country;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
