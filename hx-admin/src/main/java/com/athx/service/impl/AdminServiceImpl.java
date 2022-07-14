package com.athx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.MD5;
import com.athx.entity.Admin;
import com.athx.dao.AdminDao;
import com.athx.entity.ResponseResult;
import com.athx.entity.Result;
import com.athx.service.AdminService;
import com.athx.util.JwtUtils;
import com.athx.vos.AdminDto;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.athx.entity.Prefix;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
*
*/
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public ResponseResult getUser(String token) {
        log.error("token值"+token);
        AdminDto adminDto = (AdminDto) redisTemplate.opsForValue().get(Prefix.TOKEN_PREFIX + token);
        Admin admin = BeanUtil.copyProperties(adminDto, Admin.class);
        return Result.SuccessResult(admin);
    }

    @Override
    public ResponseResult login(Admin admin) {
        Admin selectAdmin = adminDao.selectByUserName(admin.getUsername());
        //验证用户是否存在
        if (BeanUtil.isEmpty(selectAdmin)){
            throw new RuntimeException("用户不存在");
        }
        String encode = MD5.create().digestHex16("123456");
        //验证密码
        if (!encode.equals(selectAdmin.getPassword())){
            throw new RuntimeException("密码错误");
        }
        Admin JwtAdmin = JwtUtils.setTime(selectAdmin);
        System.out.println(JwtAdmin);
        AdminDto adminDto = BeanUtil.copyProperties(JwtAdmin, AdminDto.class);
        System.out.println(adminDto);
        //生成token值
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", JwtAdmin.getId());
        map.put("username", JwtAdmin.getUsername());
        map.put("avatar", JwtAdmin.getAvatar());
        String token = JwtUtils.createJwtToken(map);
        //存入redis
        RedisUtils.saveValue(Prefix.TOKEN_PREFIX + token, adminDto,30, TimeUnit.MINUTES);
        return Result.SuccessResult(token);
    }

    @Override
    public ResponseResult logout(String token) {
        redisTemplate.delete(Prefix.TOKEN_PREFIX+token);
        return Result.SuccessResult();
    }


}
