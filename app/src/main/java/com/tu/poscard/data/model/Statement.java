package com.tu.poscard.data.model;

import java.math.BigDecimal;

/**
 * 账单
 *
 * @auther Tu
 * @date 2018/12/20
 * @email enum@foxmail.com
 */
public class Statement extends BaseModel {
    private static final long serialVersionUID = -4603070900733244012L;
    /**
     * 银行卡
     */
    private String bankcard;
    /**
     * 银行卡id {@link Bankcard#id}
     */
    private Integer bankcard_Id;
    /**
     * 应还款金额
     */
    private BigDecimal new_balance;
    /**
     * 最低还款额
     */
    private BigDecimal min_payment;
    /**
     * 本期已还款金额
     *
     * @deprecated {@link #new_payment}
     */
    @Deprecated
    private BigDecimal payment;
    /**
     * 本期已还款金额
     */
    private BigDecimal new_payment;
    /**
     * 还款日
     */
    private String payment_due_date;
    /**
     * 状态
     * {@link PaymentStatusEnum}
     */
    private int status;

    /**
     * 日历事件id
     */
    private String event_id;

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public Integer getBankcard_Id() {
        return bankcard_Id;
    }

    public void setBankcard_Id(Integer bankcard_Id) {
        this.bankcard_Id = bankcard_Id;
    }

    public BigDecimal getNew_balance() {
        return new_balance;
    }

    public void setNew_balance(BigDecimal new_balance) {
        this.new_balance = new_balance;
    }

    public BigDecimal getMin_payment() {
        return min_payment;
    }

    public void setMin_payment(BigDecimal min_payment) {
        this.min_payment = min_payment;
    }

    public String getPayment_due_date() {
        return payment_due_date;
    }

    public void setPayment_due_date(String payment_due_date) {
        this.payment_due_date = payment_due_date;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * @deprecated
     * @param payment
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getNew_payment() {
        return new_payment;
    }

    public void setNew_payment(BigDecimal new_payment) {
        this.new_payment = new_payment;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
