package com.mirai.indidea.dto.Result;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResultDto<T> implements Serializable {

    private static int PARAM_FAIL_CODE = 1002;

    private Integer code;

    private String msg;

    private T data;

    public ResultDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultDto(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultDto() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public static ResultDto error() {
        return new ResultDto(Integer.parseInt(HttpStatus.INTERNAL_SERVER_ERROR.toString()), "系统繁忙,请稍后再试");
    }


    public static ResultDto paramFail(String msg) {
        return new ResultDto(PARAM_FAIL_CODE, msg);
    }


    public static ResultDto success() {
        return new ResultDto(Integer.parseInt(HttpStatus.OK.toString()), "success");
    }

    public static ResultDto failed() {
        return new ResultDto(Integer.parseInt(HttpStatus.INTERNAL_SERVER_ERROR.toString()), "failed");
    }

    public ResultDto<T> success(T data) {
        return new ResultDto<T>(Integer.parseInt(HttpStatus.OK.toString()), "success", data);
    }

}
