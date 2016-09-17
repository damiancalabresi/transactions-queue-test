package com.dcalabresi.test.transactions.exception;

/**
 * Created by damian on 9/17/16.
 */
public abstract class ManagedException extends RuntimeException {

    public ManagedException(String message) {
        super(message);
    }
}
