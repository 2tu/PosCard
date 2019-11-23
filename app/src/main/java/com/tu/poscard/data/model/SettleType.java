package com.tu.poscard.data.model;

import java.math.BigDecimal;

/**
 * 结算方式
 *
 * @auther Tu
 * @date 2018/12/20
 * @email enum@foxmail.com
 */
public class SettleType extends BaseModel {
    private static final long serialVersionUID = 5016448494656380307L;
    /**
     * 结算方式
     */
    private String name;
    /**
     * 手续费(D+0)
     */
    private BigDecimal d_service_charge;
    /**
     * 附加费(D+0)
     */
    private BigDecimal d_extra_charge;
    /**
     * 手续费(T+1)
     */
    private BigDecimal t_service_charge;
    /**
     * 附加费(T+1)
     */
    private BigDecimal t_extra_charge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getD_service_charge() {
        return d_service_charge;
    }

    public void setD_service_charge(BigDecimal d_service_charge) {
        this.d_service_charge = d_service_charge;
    }

    public BigDecimal getD_extra_charge() {
        return d_extra_charge;
    }

    public void setD_extra_charge(BigDecimal d_extra_charge) {
        this.d_extra_charge = d_extra_charge;
    }

    public BigDecimal getT_service_charge() {
        return t_service_charge;
    }

    public void setT_service_charge(BigDecimal t_service_charge) {
        this.t_service_charge = t_service_charge;
    }

    public BigDecimal getT_extra_charge() {
        return t_extra_charge;
    }

    public void setT_extra_charge(BigDecimal t_extra_charge) {
        this.t_extra_charge = t_extra_charge;
    }
}
