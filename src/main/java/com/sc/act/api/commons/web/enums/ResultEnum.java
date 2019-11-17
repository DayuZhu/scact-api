package com.sc.act.api.commons.web.enums;

import com.sc.act.api.commons.web.base.IResultEnum;

/**
 * @ClassName ResultEnum
 * @Description 请求结果基础枚举，用于返回信息处理
 */
public enum ResultEnum implements IResultEnum {
    /**
     * 成功
     */
    SUCCESS("0", "处理成功", "处理成功"),

    /**
     * 失败
     */
    FAIL("-100", "处理失败", "处理失败"),

    /**
     * 异常
     */
    EXCEPTION("-101", "系统异常", "系统异常"),

    /**
     * 验签失败
     */
    SIGN_FAIL("-102", "处理失败", "签名校验失败"),

    /**
     * 设备型号不能为空
     */
    DEVICE_NULL("-103", "处理失败", "设备型号不能为空"),
    /**
     * 设备型号填写不正确
     */
    DEVICE_ERROR("-104", "处理失败", "设备型号填写不正确"),
    /**
     * 媒体类型不能为空
     */
    CONTENT_TYPE_NULL("-105", "处理失败", "媒体类型不能为空");

    ResultEnum(String code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    private String code;
    private String message;
    private String desc;

    public String getCode() {
        return code;
    }

    // 前台显示信息
    public String getMessage() {
        return message;
    }

    // 后台描述
    public String getDesc() {
        return desc;
    }

    public static ResultEnum toEnum(String code) {
        for (ResultEnum item : ResultEnum.values()) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }

}
