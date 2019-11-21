package com.sc.act.api.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述:
 *
 * @className:ProductPriceInfoBmo
 * @projectName:scact-api-2019-1116
 * @author:Dayu
 * @date: 2019/11/21 10:10
 */
@ApiModel(value = "商城推送请求参数", description = "商城推送请求参数")
public class ProductPriceInfoBmo {
    @ApiModelProperty("产品ID")
    private Integer productId;
    @ApiModelProperty("产品金额(分)")
    private Integer price;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
