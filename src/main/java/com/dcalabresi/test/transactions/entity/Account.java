package com.dcalabresi.test.transactions.entity;

import java.math.BigDecimal;

/**
 * Created by damian on 9/16/16.
 */
public class Account {

    private Integer id;
    private String bank;
    private String country;
    private BigDecimal balance;

    public Account() {
    }

    public Account(Integer id, String bank, String country, BigDecimal balance) {
        this.id = id;
        this.bank = bank;
        this.country = country;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
