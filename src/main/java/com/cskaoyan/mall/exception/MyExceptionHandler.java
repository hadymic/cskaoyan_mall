package com.cskaoyan.mall.exception;

import com.cskaoyan.mall.vo.BaseRespVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义全局异常处理器
 *
 * @author hadymic
 */
@ControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    /*
     * json解析异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseRespVo handleException(HttpMessageNotReadableException e) {
        if (e.getMessage().contains("JSON parse error: ")) {
            logger.warn(e.getMessage());
            return BaseRespVo.fail("参数值不对");
        } else {
            logger.warn(e.getMessage());
            return null;
        }
    }
}
