package com.sd.exception.handler;

import com.sd.exception.BadRequestException;
import com.sd.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.apache.shiro.authc.CredentialsException;
/**
 * @author siyang
 * @create 2020-01-13 13:45
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CredentialsException.class)
    public ResponseEntity incorrectCredentialsException(CredentialsException e){
        log.error("用户名或密码不正确");
        return buildResponseEntity(ApiError.error("用户名或密码不正确"));
    }
    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity badRequestException(BadRequestException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getStatus(),e.getMessage()));

    }
    /**
     * 统一返回
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
