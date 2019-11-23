package com.tu.poscard.data.model;

/**
 * @auther Tu
 * @date 2018/12/20
 * @email enum@foxmail.com
 */
public enum PaymentStatusEnum {
    OVERDUE(1, "已逾期"),
    REPAYMENT_PENDING(0, "待还款"),
    PAY_OFF(2, "已还清");

    private final int code;
    private final String message;

    PaymentStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PaymentStatusEnum code(int type) {
        for (PaymentStatusEnum typeEnum : values()) {
            if (typeEnum.code == type) {
                return typeEnum;
            }
        }
        return null;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
