package com.sc.act.api.commons.web.ex.handler;

import com.sc.act.api.commons.web.base.BaseRunTimeException;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseEntity<Result> baseRunTimeException(BaseRunTimeException exception) {
        Result vo = new Result<>();
        vo.setRetCode(exception.getExceptionCode());
        vo.setRetMsg(exception.getExceptionMessage());
        logger.error("业务异常:errorCode=" + exception.getExceptionCode()
                        + ",errorMessage=" + exception.getExceptionMessage()
                        + ",errorDesc=" + exception.getExceptionDesc()
                , exception);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Result> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Result vo = new Result<>();
        vo.setRetCode(ResultEnum.FAIL.getCode());
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
    public ResponseEntity<Result> validationException(ValidationException exception) {
        Result vo = new Result<>();
        vo.setRetCode(ResultEnum.FAIL.getCode());
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


    @ExceptionHandler({HttpServerErrorException.class, HttpClientErrorException.class})
    public ResponseEntity<Result> httpServerErrorException(Exception ex) {
        HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) ex;
        Result vo = new Result<>();
        vo.setRetCode(CommonConstant.STRING_HYPHEN + httpStatusCodeException.getStatusCode().toString());
        vo.setRetMsg(httpStatusCodeException.getMessage());
        logger.error("HttpServerError异常:errorCode=" + ResultEnum.FAIL.getCode()
                + ",errorMessage=" + ResultEnum.FAIL.getMessage()
                + ",errorDesc=" + ResultEnum.FAIL.getDesc()
                + " exceptionInfo=" + ex.getMessage()
                + "校验参数=" + httpStatusCodeException.getMessage(), ex);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<Result> bindExceptionHandler(BindException bindException) {
        String message = bindException.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());

        Result vo = new Result<>();
        vo.setRetCode(CommonConstant.STRING_HYPHEN + HttpStatus.BAD_REQUEST.toString());
        vo.setRetMsg(message);
        logger.error("BindException异常:errorCode=" + ResultEnum.FAIL.getCode()
                + ",errorMessage=" + ResultEnum.FAIL.getMessage()
                + ",errorDesc=" + ResultEnum.FAIL.getDesc()
                + " exceptionInfo=" + bindException.getMessage()
                + "校验参数=" + bindException.getMessage(), bindException);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleServiceException(Exception ex) {
        Result vo = new Result();
        vo.setRetMsg(ResultEnum.FAIL.getMessage());
        vo.setRetCode(ResultEnum.FAIL.getCode());
        logger.error("系统异常:errorCode=" + ResultEnum.FAIL.getCode()
                + ",errorMessage=" + ResultEnum.FAIL.getMessage()
                + ",errorDesc=" + ResultEnum.FAIL.getDesc()
                + " exceptionInfo=" + ex.getMessage(), ex);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

}
