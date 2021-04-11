package com.hhu.ts.utils.ResponseResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: WaterProject
 * @description: 配置自定义拦截器
 * @author: Wang JinLei
 * @create: 2021-03-13 13:02
 */
@Configuration
public class ResponseResultConfig implements WebMvcConfigurer {
    @Autowired
    private ResponseResultInterceptor responseResultInterceptor;

    /**
     * 这个方法是用来注册拦截器，我们自己写好的拦截器需要通过这里注册才有效
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(responseResultInterceptor).addPathPatterns("/**");
    }
}
