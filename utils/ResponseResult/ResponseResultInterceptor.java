package com.hhu.ts.utils.ResponseResult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @program: WaterProject
 * @description: 拦截请求，是否此请求返回的值需要包装，其实就是在运行的时候解析@ResponseResult注解，此代码核心思想，就是获取此请求，是否需要返回值包装，设置一个属性标记。
 * @author: Wang JinLei
 * @create: 2021-03-12 20:38
 */
@Slf4j
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*请求的方法*/
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            /*判断是否在类对象上添加了注解*/
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                /*设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断*/
                request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
            }
            /*方法体上是否有注解*/
            else if (method.isAnnotationPresent(ResponseResult.class)) {
                /*设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断*/
                request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
