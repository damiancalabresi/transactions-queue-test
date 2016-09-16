package com.dcalabresi.test.transactions.controller;

import com.dcalabresi.test.transactions.dto.TransactionDto;
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

    @RequestMapping(method = RequestMethod.POST, value = "")
    public TransactionDto getString(@RequestBody TransactionDto dto) {
        return dto;
    }
}
