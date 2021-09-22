package com.sl.admin.model;

import lombok.Data;

import java.io.Serializable;

/**
 * common return
 *
 * @param <T>
 * @author sl 2021-09-14 16:32:31
 */
@Data
public class BaseResponse<T> implements Serializable {

    private static final int SUCCESS_CODE = 200;
    private static final int FAIL_CODE = 500;

    private static final long serialVersionUID = 7542184640342309896L;

    private int code;
    private String msg;
    private T content;


    public static <T> BaseResponse success(T data) {
        return new BaseResponse(data);
    }

    public static <T> BaseResponse fail(String msg) {
        return new BaseResponse(FAIL_CODE, msg);
    }


    private BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private BaseResponse(T content) {
        this.code = SUCCESS_CODE;
        this.content = content;
        this.msg= "success";
    }

    public BaseResponse() {
    }

    @Override
    public String toString() {
        return "ReturnT [code=" + code + ", msg=" + msg + ", content=" + content + "]";
    }

}
