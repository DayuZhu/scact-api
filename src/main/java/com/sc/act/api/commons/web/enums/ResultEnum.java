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
    PRODUCT_ISNOT_WINNER("-113", "产品未配置中奖名单", "产品未配置中奖名单"),
    PRODUCT_ISNOT_WINNER_REL("-114", "产品未配置中奖名单关系", "产品未配置中奖名单关系"),


    PRODUCT_WINNER_AMOUNT_ERROR("-115", "产品中奖金额错误", "产品中奖金额错误"),
    PRODUCT_ACCOUNT_ISNOT_EXIST("-116", "产品没有对应的账户信息", "产品没有对应的账户信息"),
    PRODUCT_OUT_PRODUCTID_IS_ERROR("-117", "产品不能绑定多个外部产品ID", "产品不能绑定多个外部产品ID"),
    PRODUCT_OUT_PRODUCTID_B2C_ERROR("-118", "调用第三方创建产品返回码错误", "调用第三方创建产品返回码错误"),
    PRODUCT_OUT_PRODUCTID_B2C_NULL_ERROR("-119", "调用第三方创建产品返回实体错误", "调用第三方创建产品返回实体错误"),
    PRODUCT_INFO_NULL_ERROR("-121", "调用第三方创建产品信息为空", "调用第三方创建产品信息为空"),
    PRODUCT_TICKET_INFO_ERROR("-122", "没用匹配到券信息", "没用匹配到券信息"),
    USER_MONEY_ERROR("-123", "分账失败", "分账失败"),
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
