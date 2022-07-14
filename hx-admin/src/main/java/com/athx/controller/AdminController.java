package com.athx.controller;

import com.athx.entity.Admin;
import com.athx.entity.ResponseResult;
import com.athx.entity.Result;
import com.athx.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/11 21:07
 * @description：管理员信息控制器
 * @modified By：
 * @version: $
 */
@RestController
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 通过Token值获取用户登录
     * @param token
     * @return
     */
    @GetMapping("/admin-user")
    public ResponseResult<Admin> getUer(@RequestHeader("Authorization") String token){
        log.info("token值{}",token);
        return adminService.getUser(token);
    }

    /**
     * 退出登录并删除token信息
     * @return
     */
    @DeleteMapping("/logout")
    public ResponseResult logout(@RequestHeader("Authorization") String token){
        return adminService.logout(token);
    }
    /**
     *登录并返回token信息
     * @param admin
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody Admin admin, HttpSession session){
        return adminService.login(admin);
    }

}
