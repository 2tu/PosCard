package com.tu.poscard.data.model;

/**
 * 卡类型
 */
public enum CardTypeEnum {

    CREDIT("Credit", "信用卡"),
    DEBIT("Debit", "借记卡");

    private final String code;
    private final String message;

    CardTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CardTypeEnum code(String type) {
        for (CardTypeEnum typeEnum : values()) {
            if (typeEnum.code.equals(type)) {
                return typeEnum;
            }
        }
        return null;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
