package com.cskaoyan.mall.exception;

import com.cskaoyan.mall.vo.BaseRespVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 商品上架，编辑校验
 * @author stark_h
 */
@RestControllerAdvice
public class GoodsExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理请求对象属性不满足校验规则的异常信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseRespVo exception(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();

        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage() + "\n");
        }
        return BaseRespVo.fail(builder.toString());
    }

    /**
     * 处理请求单个参数不满足校验规则的异常信息
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public BaseRespVo constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException exception) {
        logger.info(exception.getMessage());
        return BaseRespVo.fail(exception.getMessage());
    }


    /**
     * 处理未定义的其他异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public BaseRespVo exceptionHandler(HttpServletRequest request, Exception exception) {
        logger.error(exception.getMessage());
        return BaseRespVo.fail(exception.getMessage());
    }
}
