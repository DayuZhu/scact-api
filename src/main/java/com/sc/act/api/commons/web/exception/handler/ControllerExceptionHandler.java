package com.sc.act.api.commons.web.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import com.sc.act.api.commons.web.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sc.act.api.commons.web.base.BaseRunTimeException;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.commons.web.base.Result;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
        logger.error("业务异常:errorCode=" + exception.getExceptionCode()
                        + ",errorMessage=" + exception.getExceptionMessage()
                        + ",errorDesc=" + exception.getExceptionDesc()
                , exception);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Result<String>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Result<String> vo = new Result<>();
        vo.setRetCode(ResultEnum.FAIL.getCode());
        vo.setData(null);
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        errors.forEach(x -> errorMsg.append(x.getDefaultMessage()).append(CommonConstant.STRING_SEMICOLON));
        StringBuilder message = new StringBuilder();
        Iterator iterator = errors.iterator();

        if (iterator.hasNext()) {
            ObjectError error = (ObjectError) iterator.next();
            message.append(((FieldError) error).getField());
            message.append(CommonConstant.STRING_COLON);
            message.append(error.getDefaultMessage());
            message.append(CommonConstant.STRING_SEMICOLON);
        }
        vo.setRetMsg(message.toString());
        logger.error("参数校验异常:errorCode=" + ResultEnum.FAIL.getCode()
                + ",errorMessage=" + ResultEnum.FAIL.getMessage()
                + ",errorDesc=" + ResultEnum.FAIL.getDesc()
                + " exceptionInfo=" + exception.getMessage()
                + "校验参数=" + message.toString(), exception);
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

        vo.setData(null);
        ConstraintViolationException e = (ConstraintViolationException) exception;
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        Iterator iterator = violations.iterator();
        if (iterator.hasNext()) {
            ConstraintViolation<?> violation = (ConstraintViolation) iterator.next();
            strBuilder.append(violation.getPropertyPath().toString());
            strBuilder.append(CommonConstant.STRING_COLON);
            strBuilder.append(violation.getMessage());
        }
        String string = strBuilder.toString();
        vo.setRetMsg(string);
        logger.error("参数校验异常:errorCode=" + ResultEnum.FAIL.getCode()
                + ",errorMessage=" + ResultEnum.FAIL.getMessage()
                + ",errorDesc=" + ResultEnum.FAIL.getDesc()
                + " exceptionInfo=" + exception.getMessage()
                + "校验参数=" + string, exception);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    /**
     * @param throwable
     * @return
     * @Title handleServiceException
     * @Description 统一异常处理，捕获Exception异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Result<String>> handleServiceThrowable(Throwable throwable) {
        Result<String> vo = new Result<>();
        vo.setRetCode(ResultEnum.FAIL.getCode());
        vo.setRetMsg(ResultEnum.FAIL.getMessage());
        vo.setData(null);
        logger.error("系统异常:errorCode=" + ResultEnum.FAIL.getCode()
                + ",errorMessage=" + ResultEnum.FAIL.getMessage()
                + ",errorDesc=" + ResultEnum.FAIL.getDesc()
                + " exceptionInfo=" + throwable.getMessage(), throwable);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }
}
