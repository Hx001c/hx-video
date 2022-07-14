package com.athx.config;

import com.athx.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/12 15:26
 * @description：拦截器配置文件
 * @modified By：
 * @version: $
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器的方式，可直接new一个对象，
     * registry.addInterceptor(new ParamInterceptor())，
     * 但通过手动new出来的拦截器中，无法使用@Autowired 和 @Value 加载对象和配置文件参数。
     *
     * 所以需要在添加拦截器此处，通过@Bean 注解，意味着将这个对象
     * 交给spring管理。那么该拦截器才可以使用@Value等spring管理的注解
     * @return
     */
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**") //拦截的地址
                .excludePathPatterns("/swagger/**","/login","/admin/login","/admin/test","/test","/user","/admin/user","**/error/**"); //不需要拦截的地址，如登录接口
    }
}
