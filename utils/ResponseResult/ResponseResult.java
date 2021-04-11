package com.hhu.ts.utils.ResponseResult;

import java.lang.annotation.*;

/**
 * @program: WaterProject
 * @description: 返回信息注解
 * @author: Wang JinLei
 * @create: 2021-03-12 20:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
