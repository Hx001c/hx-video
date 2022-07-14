package com.athx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/11 22:55
 * @description：响应数据
 * @modified By：
 * @version: $
 */
@ApiModel(value = "ResponseResult",description = "响应结果数据")
@Data
public class ResponseResult<T> {
    @ApiModelProperty(value = "状态码(200:成功，400:失败，401:无权限，404:接口不存在，500:服务器内部错误)")
    private int code;
    @ApiModelProperty(value = "状态信息")
    private String msg;
    @ApiModelProperty(value = "返回数据，一般为json对象或json数组")
    private T data;
    public ResponseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public ResponseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseResult setData(T data) {
        this.data = data;
        return this;
    }

}
