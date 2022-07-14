package com.athx;

import com.athx.dao.AdminDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/12 21:24
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootTest
public class AdminDaoTest {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test(){
        System.out.println(adminDao);
        redisTemplate.opsForValue().set("key","value");
    }
}
