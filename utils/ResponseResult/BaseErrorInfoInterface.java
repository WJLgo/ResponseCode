package com.hhu.ts.utils.ResponseResult;

/**
 * @program: WaterProject
 * @description:
 * @author: Wang JinLei
 * @create: 2021-03-13 20:03
 */
public interface BaseErrorInfoInterface {
    /** 错误码*/
    String getResultCode();

    /** 错误描述*/
    String getResultMsg();
}
