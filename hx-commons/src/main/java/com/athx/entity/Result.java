package com.athx.entity;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/11 23:07
 * @description：获取响应结果
 * @modified By：
 * @version: $
 */
public class Result {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static ResponseResult SuccessResult() {
        return new ResponseResult()
                .setCode(ResultCode.SUCCESS.code())
                .setMsg(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> ResponseResult<T> SuccessResult(T data) {
        return new ResponseResult()
                .setCode(ResultCode.SUCCESS.code())
                .setMsg(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static <T> ResponseResult<T> SuccessResult(String message, T data) {
        return new ResponseResult()
                .setCode(ResultCode.SUCCESS.code())
                .setMsg(message)
                .setData(data);
    }

    public static ResponseResult FailResult(String message) {
        return new ResponseResult()
                .setCode(ResultCode.FAIL.code())
                .setMsg(message);
    }

    public static <T> ResponseResult<T> FailResult(String message, T data) {
        return new ResponseResult()
                .setCode(ResultCode.FAIL.code())
                .setMsg(message)
                .setData(data);
    }
public static void main(String[] args) {
    System.out.println(ResultCode.FAIL);
}
}

