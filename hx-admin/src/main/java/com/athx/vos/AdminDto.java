package com.athx.vos;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/12 9:05
 * @description：Admin用户信息类的精简版本
 * @modified By：
 * @version: $
 */
@Data
public class AdminDto {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    @JsonProperty("name")//用于指定转化成json数据格式后显示name而不是username
    private String username;
    /**
     * 用户头像地址
     */
    private String avatar;
    /**
     * 登录时间
     * */
    private Long loginTime ;

    /**
     * 令牌过期时间
     * */
    private Long expireTime ;

}
