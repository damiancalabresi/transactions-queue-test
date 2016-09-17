package com.dcalabresi.test.transactions.exception;

/**
 * Created by damian on 9/17/16.
 */
public class AccountDoesNotExistException extends ManagedException {
    public AccountDoesNotExistException() {
        super("The account id received doesn't belong to an existent account");
    }
}
