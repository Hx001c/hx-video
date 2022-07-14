package com.athx.interceptor;

import com.athx.entity.Admin;
import com.athx.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.HashMap;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/12 15:11
 * @description：JWT拦截器
 * @modified By：
 * @version: $
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<Object, Object> map = new HashMap<>();
        /**
         * 疑问1：什么是options请求
         *
         * OPTIONS请求方法的主要用途有两个：
         *
         * 1、获取服务器支持的HTTP请求方法；
         *
         * 2、用来检查服务器的性能。例如：AJAX进行跨域请求时的预检，需要向另外一个域名的资源发送一个HTTP OPTIONS请求头，用以判断实际发送的请求是否安全。
         *
         * 这是浏览器给我们加上的，后端并没有做任何操作。
         *
         * 疑问2：为什么会用到options请求
         *
         * 这得从浏览器同源策略和跨域说起，具体可阅读也谈谈同源策略和跨域问题和浏览器同源政策及其规避方法，这里不在赘述。
         *
         * 解决跨域问题的方法有很多种，CORS是比较好的解决方案，我们的项目也是用的这种模式，这个模式会有”预检”的请求，也就是正常请求之前的options请求。
         */
        //获取请求方法
        try {
            String method = request.getMethod();
            if ("OPTIONS".equalsIgnoreCase(method)){
                //如果是OPTIONS请求,直接放行
                return true;
            }
            //从请求中获取令牌
            String jwtToken = request.getHeader(tokenHeader);
            if (jwtToken == "" || jwtToken == null){
                throw new SignatureException("令牌非法");
            }
            if(jwtToken.startsWith(tokenHead)){
                jwtToken = jwtToken.substring(tokenHead.length());
            }
            //验证token
            Admin admin = JwtUtils.verifyJwtToken(jwtToken);
            //验证成功后,如果令牌有效时间<=5分钟,则签发新的令牌,刷新令牌时间
            if(admin != null){
                if(admin.getExpireTime() - System.currentTimeMillis() <= 1000L * 60 * 5){
                    JwtUtils.refreshToken(admin);
                }
                return  true ;
            }else{
                map.put("success",false);
                map.put("code",401);
                map.put("message","令牌已失效,请重新登录");
            }
        } catch(SignatureException e){
            e.printStackTrace();
            map.put("message","令牌不合法");
            map.put("code",401);
            map.put("success",false);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("message","令牌验签失败:"+e.getMessage());
            map.put("success",false);
        }
        String jsonMap = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(jsonMap);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;
}
