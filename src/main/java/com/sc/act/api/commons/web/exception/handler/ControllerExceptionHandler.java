package com.sc.act.api.commons.web.exception.handler;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sc.act.api.commons.web.base.BaseRunTimeException;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.commons.web.base.Result;

/**
 * @ClassName ControllerExceptionHanler
 * @Description 异常捕获
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * @param exception
     * @return
     * @Title baseRunTimeException
     * @Description 统一异常处理，捕获BaseRunTimeException异常
     */
    @ExceptionHandler(BaseRunTimeException.class)
    public ResponseEntity<Result<String>> baseRunTimeException(BaseRunTimeException exception) {
        Result<String> vo = new Result<>();
        vo.setRetCode(exception.getExceptionCode());
        vo.setRetMsg(exception.getExceptionMessage());
        vo.setData(null);
        logger.error("业务异常:errorCode={},errorMessage={},errorDesc={}", exception.getExceptionCode(),
                exception.getExceptionMessage(), exception.getExceptionDesc());
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    /**
     * @param exception
     * @return
     * @Title validationException
     * @Description 统一异常处理，捕获ValidationException异常
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Result<String>> validationException(ValidationException exception) {
        Result<String> vo = new Result<>();
        vo.setRetCode(ResultEnum.FAIL.getCode());
        vo.setRetMsg(ResultEnum.FAIL.getCode());
        vo.setData(null);
        logger.error("参数校验异常:errorCode={},errorMessage={},errorDesc={} exceptionInfo={}", ResultEnum.FAIL.getCode(),
                ResultEnum.FAIL.getMessage(), ResultEnum.FAIL.getDesc(), exception.getMessage());
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    /**
     * @param exception
     * @return
     * @Title handleServiceException
     * @Description 统一异常处理，捕获Exception异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Result<String>> handleServiceThrowable(Exception exception) {
        Result<String> vo = new Result<>();
        vo.setRetCode(ResultEnum.FAIL.getCode());
        vo.setRetMsg(ResultEnum.FAIL.getMessage());
        vo.setData(null);
        logger.error("系统异常:errorCode={},errorMessage={},errorDesc={} exceptionInfo={}", ResultEnum.FAIL.getCode(),
                ResultEnum.FAIL.getMessage(), ResultEnum.FAIL.getDesc(), exception.getMessage());
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }
}
