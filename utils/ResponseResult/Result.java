package com.hhu.ts.utils.ResponseResult;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: WaterProject
 * @description: 返回信息bean
 * @author: Wang JinLei
 * @create: 2021-03-12 20:09
 */
@Data
public class Result implements Serializable {
    //    private ResultCode resultCode;
    private Integer code;
    private String message;
    /*返回对象*/
    private Object data;

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    /**
     * 返回成功，不含对象
     *
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 返回成功，含对象
     *
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 返回失败，不含对象
     *
     * @param resultCode
     * @return
     */
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回失败，含对象
     *
     * @param resultCode
     * @param data
     * @return
     */
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

}
