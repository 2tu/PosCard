package com.tu.poscard.data.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 包装结算方式，用于显示
 * @auther Tu
 * @date 2018/12/20
 * @email enum@foxmail.com
 */
public class WrapSettleType implements Serializable {
    private static final long serialVersionUID = -6087255237899776471L;
    /**
     * 名称 - 方式
     */
    private String name;
    /**
     * 手续费
     */
    private BigDecimal serviceCharge;
    /**
     * 附加费
     */
    private BigDecimal extraCharge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getExtraCharge() {
        return extraCharge;
    }

    public void setExtraCharge(BigDecimal extraCharge) {
        this.extraCharge = extraCharge;
    }
}
