package com.dcalabresi.test.transactions.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by damian on 9/16/16.
 */

@RestController("/")
public class AccountController {

    @RequestMapping(method = RequestMethod.GET, value = "test/{param}")
    public String getString(@PathVariable String param) {
        return "Hola Mundo: " + param;
    }
}
