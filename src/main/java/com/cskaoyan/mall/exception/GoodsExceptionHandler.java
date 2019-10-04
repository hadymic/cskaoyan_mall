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
       /*  StringBuilder builder = new StringBuilder();
       for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage() + "\n");
        }
        //优化，不应该返回全部错误信息，只返回一条
        return BaseRespVo.fail(builder.toString());*/

        List<FieldError> fieldErrors1 = fieldErrors.subList(0, 1);
        FieldError fieldError = fieldErrors1.get(0);
       // FieldError fieldError = fieldErrors.stream().findFirst().orElse(null);
        String message = fieldError.getDefaultMessage();

        return BaseRespVo.fail(message);
    }

    /**
     * 处理请求单个参数不满足校验规则的异常信息
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public BaseRespVo constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException exception) {
        logger.info(exception.getMessage());
        //return BaseRespVo.fail(exception.getMessage());
        return BaseRespVo.fail("参数值错误");
    }


    /**
     * 处理未定义的其他异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public BaseRespVo exceptionHandler(HttpServletRequest request, Exception exception) {
        logger.error(exception.getMessage());
        //return BaseRespVo.fail(exception.getMessage());
        return BaseRespVo.fail("参数值错误");
    }
}
