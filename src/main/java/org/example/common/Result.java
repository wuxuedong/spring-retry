package org.example.common;

import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author wxd
 * @create 2022-11-07 13:56
 */
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //成功码
    public static final Integer SUCCESS_CODE = 200;
    //成功消息
    public static final String SUCCESS_MSG = "SUCCESS";

    //失败
    public static final Integer ERROR_CODE = 201;
    public static final String ERROR_MSG = "系统异常,请联系管理员";
    //没有权限的响应码
    public static final Integer NO_AUTH_COOD = 999;

    public Result(Integer successCode, String successMsg, T data) {
        this.code = successCode;
        this.msg = successMsg;
        this.data = data;
    }

    public Result(){

    }

    //执行成功
    public static <T> Result<T> success(T data){
        return new Result<>(SUCCESS_CODE,SUCCESS_MSG,data);
    }
    //执行失败
    public static <T> Result failed(String msg){
        msg = StringUtils.isEmpty(msg)? ERROR_MSG : msg;
        return new Result(ERROR_CODE,msg,"");
    }
    //传入错误码的方法
    public static <T> Result failed(int code,String msg){
        msg = StringUtils.isEmpty(msg)? ERROR_MSG : msg;
        return new Result(code,msg,"");
    }
    //传入错误码的数据
    public static <T> Result failed(int code,String msg,T data){
        msg = StringUtils.isEmpty(msg)? ERROR_MSG : msg;
        return new Result(code,msg,data);
    }
}
