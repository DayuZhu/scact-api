package com.sc.act.api.commons.web.base;


import com.sc.act.api.commons.web.enums.ResultEnum;

/**
 * @param <T>
 */
public class Result<T> {

    private String retCode;

    private String retMsg;

    private T data;

    public Result() {
        this.retCode = ResultEnum.SUCCESS.getCode();
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
