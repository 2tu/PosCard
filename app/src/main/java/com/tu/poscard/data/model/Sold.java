package com.tu.poscard.data.model;

import java.math.BigDecimal;

/**
 * 交易流水
 * @auther Tu
 * @date 2018/12/11
 * @email enum@foxmail.com
 */
public class Sold extends BaseModel {
    private static final long serialVersionUID = -1311104897048283987L;
    private String sold;
    private String description;
    private BigDecimal amount;
    /**
     * 银行卡
     */
    private String bankCard;
    /**
     * 银行卡id {@link Bankcard#id}
     */
    private Integer bankcard_Id;
    private String transType;
    private BigDecimal serviceCharge;
    /**
     * 附加费
     */
    private BigDecimal extraCharge;

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
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

    public Integer getBankcard_Id() {
        return bankcard_Id;
    }

    public void setBankcard_Id(Integer bankcard_Id) {
        this.bankcard_Id = bankcard_Id;
    }
}
