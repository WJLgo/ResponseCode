package com.hhu.ts.utils.ResponseResult;

/**
 * @program: WaterProject
 * @description: 全局异常处理
 * @author: Wang JinLei
 * @create: 2021-03-13 22:26
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**

 * @program: WaterProject

 * @description:

 * @author: Wang JinLei

 * @create: 2021-03-13 20:07
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**

     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest req, BizException e){
        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return Result.failure(ResultCode.CUSTOM_EXCEPTION,e.getErrorMsg());
    }

    /**

     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return Result.failure(ResultCode.NULL_POINTER_EXCEPTION,e.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e){
        logger.error("参数为空！原因是:",e.getBindingResult().getFieldError().getDefaultMessage());
        return Result.failure(ResultCode.PARAM_IS_BLANK,e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        return Result.failure(ResultCode.OTHER_ERRORS,e.getMessage());
    }

}


