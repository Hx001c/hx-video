package com.athx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/6 21:15
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String test(){
        System.err.println("users-test");
        return "users-test";
    }
}