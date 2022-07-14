package com.athx.controller;

import com.athx.entity.Admin;
import com.athx.entity.ResponseResult;
import com.athx.entity.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/6 21:15
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        System.err.println("admins-test");
        return "admins-test";
    }

}
