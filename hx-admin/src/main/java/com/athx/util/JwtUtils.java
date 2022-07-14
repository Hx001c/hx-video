package com.athx.util;

import cn.hutool.core.bean.BeanUtil;
import com.athx.entity.Admin;
import com.athx.entity.Prefix;
import com.athx.vos.AdminDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/12 14:52
 * @description：JWT工具类
 * @modified By：
 * @version: $
 */
@Component
public class JwtUtils {
    /**
     * 自定义秘钥
     *
     * */
    private static String secret;

    /**
     * jwtToken的默认有效时间 单位分钟
     * */
    private static int expireTime;

    @Value("${jwt.secret}")
    public void setSign(String secret){
        JwtUtils.secret = secret;
    }

    @Value("${jwt.expireTime}")
    public void setExpireTime(int expireTime1){
        JwtUtils.expireTime = expireTime1;
    }

    /**
     * 生成jwt token
     * @param map  要存放负载信息
     * */
    public static String createJwtToken(Map<String,Object> map){
        return  Jwts.builder()
                .setClaims(map) //放入payLoad部分的信息
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }


    /**
     * 从令牌中获取数据,就是payLoad部分存放的数据。如果jwt被改，该函数会直接抛出异常
     * @param token  令牌
     * */
    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证用户信息
     * @param token  jwtToken
     * */
    public static Admin verifyJwtToken(String token){
        Claims claims = parseToken(token);
        System.out.println(claims);
        //从redis中获取用户信息
        Object redisAdmin = RedisUtils.getValue(Prefix.TOKEN_PREFIX + token);
        AdminDto adminDto= (AdminDto) redisAdmin;
        Admin admin = BeanUtil.copyProperties(adminDto, Admin.class);
        return admin ;
    }


    /**
     * 刷新令牌时间，刷新redis缓存时间
     * @param  admin 用户信息
     * */
    public static void refreshToken(Admin admin){
        //重新设置Admin对象的过期时间，再刷新缓存
        admin.setExpireTime(System.currentTimeMillis()+1000L * 60 * expireTime);
        RedisUtils.saveValue(admin.getId()+"",admin,expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户的登录时间和令牌有效时间
     * @param admin
     * @return
     */
    public static Admin setTime(Admin admin){
        admin.setExpireTime(System.currentTimeMillis()+1000L * 60 * expireTime);
        admin.setLoginTime(System.currentTimeMillis());
        return admin;
    }
}
