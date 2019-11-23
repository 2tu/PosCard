package com.tu.poscard.data.model;

import java.math.BigDecimal;

/**
 * 银行卡
 *
 * @auther Tu
 * @date 2018/12/14
 * @email enum@foxmail.com
 */
public class Bankcard extends BaseModel {
    private static final long serialVersionUID = 4424429747232270210L;
    /**
     * 银行卡号
     */
    private String cardno;
    /**
     * 别名
     */
    private String nik_name;
    /**
     * 持卡人姓名
     */
    private String name;
    /**
     * 银行
     */
    private String bank;
    /**
     * 银行id
     */
    private Integer bank_id;
    /**
     * 卡类型
     * 借记卡Debit Card
     * 信用卡Credit Card
     */
    private String card_type;
    /**
     * 授信额度
     */
    private BigDecimal credit_limit;
    /**
     * 账单日
     */
    private String statement_date;
    /**
     * 还款日
     */
    private String payment_due_date;
    /**
     * 还款日延后多少天
     */
    private String payment_due_day;
    /**
     * 备注
     */
    private String description;

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getNik_name() {
        return nik_name;
    }

    public void setNik_name(String nik_name) {
        this.nik_name = nik_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getBank_id() {
        return bank_id;
    }

    public void setBank_id(Integer bank_id) {
        this.bank_id = bank_id;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getStatement_date() {
        return statement_date;
    }

    public void setStatement_date(String statement_date) {
        this.statement_date = statement_date;
    }

    public String getPayment_due_date() {
        return payment_due_date;
    }

    public void setPayment_due_date(String payment_due_date) {
        this.payment_due_date = payment_due_date;
    }

    public String getPayment_due_day() {
        return payment_due_day;
    }

    public void setPayment_due_day(String payment_due_day) {
        this.payment_due_day = payment_due_day;
    }

    public BigDecimal getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(BigDecimal credit_limit) {
        this.credit_limit = credit_limit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
