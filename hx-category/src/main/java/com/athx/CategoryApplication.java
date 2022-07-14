package com.athx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/6 8:58
 * @description：categroy的启动类
 * @modified By：
 * @version: $
 */
@EnableSwagger2
@SpringBootApplication
public class CategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class,args);
    }
}
