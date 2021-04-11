package com.hhu.ts.utils.ResponseResult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: WaterProject
 * @description: 重写返回体
 * @author: Wang JinLei
 * ResponseBodyAdvice接口是在Controller执行return之后，在response返回给浏览器或者APP客户端之前，执行的对response的一些处理。可以实现对response数据的一些统一封装或者加密等操作。
 * @create: 2021-03-12 21:00
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    /**
     * 标记名称
     */
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    /**
     * 是否请求包含了包装注解标记，如果没有就直接饭hi，不需要重写返回体。
     * 通过supports方法，我们可以选择哪些类，或者哪些方法要对response进行处理，其余的则不处理。
     *
     * @param returnType
     * @param converterType
     * @return
     */

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        /**
         * 判断请求 是否有包装标记.
         * request.getAttribute(RESPONSE_RESULT_ANN)就是ResponseResultInterceptor拦截器中，如果需要包装即有ResponseResult注解
         */
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResultAnn == null ? false : true;
    }

    /**
     * beforeBdoyWrite方法中，为对response处理的具体代码。只有在supports方法中通过（true）的对象才会执行
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        /**
         * 只有通过了supports方法，即有RESPONSE_RESULT_ANN标记，即有@ResponseResult注解的才会进入返回体。
         */
        System.out.println(body + " 进入 返回体 重写格式 处理中。。。。");
        /**
         * 进入返回体,重写返回值
         */
        log.info(body.getClass().getName() + " 进入返回体，重写格式处理中。。。");
        if (body == null) {
            return Result.failure(ResultCode.NO_MATCHING_DATA_FOUND);
        }
        if (body instanceof Result) {
            /**
             * 处理异常
             */
//            Exception exception=(Exception) body;

//            return Result.failure((ErrorResult) body);
            return body;
        }
        return Result.success(body);
    }
}
