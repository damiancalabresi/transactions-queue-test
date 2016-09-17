package com.dcalabresi.test.transactions.controller;

import com.dcalabresi.test.transactions.dto.TransactionDto;
import com.dcalabresi.test.transactions.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by damian on 9/16/16.
 */

@RestController
@RequestMapping("/api/transaction/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public void addTransaction(@RequestBody TransactionDto dto) {
        transactionService.addTransaction(dto.getOrigId(), dto.getDestId(), dto.getAmount());
    }
}
