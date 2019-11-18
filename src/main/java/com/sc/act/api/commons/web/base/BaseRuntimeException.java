package com.sc.act.api.commons.web.base;

import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.util.StringHelper;

/**
 * 运行时异常处理基类
 *
 * @ClassName BaseRuntimeException
 * @Description 系统基础异常类，每个系统需要扩展定义自己的异常类；继承自本类并修改sysKey<br/>
 * 并提供前4种构造方法，去掉构造方法中的sysKey参数<br/>
 * 所有枚举类型均实现IExceptionEnum接口
 * @updatedescription 去除sysKey id 以及其他静态方法
 */

public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 8279619225047725584L;

    /**
     * 异常信息，包含必要的上下文业务信息，用于打印日志
     */
    private String exceptionMessage;

    /**
     * 具体异常码，由各具体异常实例化时自己定义
     */
    private String exceptionCode;

    /**
     * 异常码描述
     */
    private String exceptionDesc;

    public BaseRuntimeException() {

    }

    public BaseRuntimeException(IResultEnum exEnum) {
        this(exEnum.getCode(), exEnum.getMessage(), exEnum.getDesc());
    }

    public BaseRuntimeException(IResultEnum exEnum, Throwable e) {
        this(exEnum.getCode(), exEnum.getMessage(), exEnum.getDesc());
        initCause(e);
    }

    public BaseRuntimeException(IResultEnum exEnum, String message) {
        this(exEnum.getCode(), message, exEnum.getDesc());
    }

    public BaseRuntimeException(IResultEnum exEnum, String message, Throwable e) {
        this(exEnum.getCode(), message, exEnum.getDesc());
        initCause(e);
    }

    protected BaseRuntimeException(String code, String message) {
        super();
        this.exceptionCode = code;
        this.exceptionMessage = message;
        this.exceptionDesc = StringHelper.stringAssembly(code, CommonConstant.STRING_COLON, message);
    }

    protected BaseRuntimeException(String code, String message, String desc) {
        super();
        this.exceptionCode = code;
        this.exceptionMessage = message;
        this.exceptionDesc = desc;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

}
