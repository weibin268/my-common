package com.zhuang.common.model;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {

    private int code = 0;
    private String message;
    private T data;

    public static ApiResult alert(String message) {
        ApiResult result = new ApiResult();
        result.setCode(-1);
        result.setMessage(message);
        return result;
    }

    public static ApiResult error(String message) {
        ApiResult result = new ApiResult();
        result.setCode(1);
        result.setMessage(message);
        return result;
    }

    public static ApiResult error(int code, String message) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static ApiResult error(String message, Object data) {
        ApiResult result = new ApiResult();
        result.setCode(1);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static ApiResult error(int code, String message, Object data) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static ApiResult success() {
        return new ApiResult();
    }

    public static ApiResult success(Object data) {
        ApiResult result = new ApiResult();
        result.setData(data);
        return result;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
