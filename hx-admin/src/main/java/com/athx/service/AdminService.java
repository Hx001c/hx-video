package com.athx.service;

import com.athx.entity.Admin;
import com.athx.entity.ResponseResult;
import com.baomidou.mybatisplus.service.IService;

/**
 *
 */
public interface AdminService extends IService<Admin> {
    /**
     * 根据传递的token获取基础用户信息
     * @param token
     * @return
     */
    public ResponseResult getUser(String token);

    /**
     * 登录,参数是admin,返回token值
     * @param admin
     * @return
     */
    public ResponseResult login(Admin admin);

    /**
     * 退出登录时根据token值删除redis中的值
     * @param token
     * @return
     */
    ResponseResult logout(String token);
}
