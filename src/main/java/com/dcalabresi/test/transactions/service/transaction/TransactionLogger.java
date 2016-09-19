package com.dcalabresi.test.transactions.service.transaction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by damian on 9/18/16.
 */
public enum TransactionLogger {
    INSTANCE;

    private BufferedWriter fileWriter;

    TransactionLogger() {
        try {
            this.fileWriter = Files.newBufferedWriter(Paths.get("transactions.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logStart(Integer transactionId) {
        writeFile("Transaction " + transactionId + " starting");
    }

    public void logError(Integer transactionId, Exception ex) {
        writeFile("Transaction " + transactionId
                + " finished with errors. Exception: "
                + ex.getClass().getSimpleName()
                + " - Message: " + ex.getMessage());
    }

    public void logSuccess(Integer transactionId, String successMessage) {
        writeFile("Transaction " + transactionId
                + " finished with success. Message: "
                + successMessage);
    }

    private void writeFile(String aString) {
        try {
            if (fileWriter != null) {
                fileWriter.write(aString + "\n");
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
