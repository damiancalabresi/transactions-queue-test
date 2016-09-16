package com.dcalabresi.test.transactions.controller;

import com.dcalabresi.test.transactions.dto.AccountDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by damian on 9/16/16.
 */

@RestController
@RequestMapping("/api/account/")
public class AccountController {

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public AccountDto getString(@PathVariable Integer id) {
        return new AccountDto(id, "CitiBank", "USA", BigDecimal.valueOf(512, 2));
    }
}
