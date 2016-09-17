package com.dcalabresi.test.transactions.exception;

/**
 * Created by damian on 9/17/16.
 */
public class TransactionRejectedException extends ManagedException {
    public TransactionRejectedException() {
        super("The transaction can't be processed in this moment");
    }
}
