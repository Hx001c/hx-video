package com.athx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/6 8:56
 * @description：
 * @modified By：
 * @version: $
 */
@EnableSwagger2
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
