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
    CONTENT_TYPE_NULL("-105", "处理失败", "媒体类型不能为空"),

    ACTIVITY_NULL("-106", "活动不能为空", "活动不能为空"),

    ACTIVITY_COUPON_NO("-108", "没有可用券", "没有可用券"),

    PRODUCT_ISNOT_EXIST("-109", "产品不存在", "产品不存在"),
    PRODUCT_TICKET_ISNOT_EXIST("-111", "产品券不存在", "产品券不存在"),

    ACTIVITY_COUPON_INSUFFICIENT("-107", "券数量金额不足", "券数量金额不足");

    ResultEnum(String code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    private String code;
    private String message;
    private String desc;

    @Override
    public String getCode() {
        return code;
    }


    /**
     * B端显示信息
     *
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }


    /**
     * 后台描述
     *
     * @return
     */
    @Override
    public String getDesc() {
        return desc;
    }


}
