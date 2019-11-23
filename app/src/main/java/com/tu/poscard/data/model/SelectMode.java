package com.tu.poscard.data.model;

import java.io.Serializable;

public enum SelectMode implements Serializable {
    MULTI(1),
    SINGLE(0),
    NONE(-1);

    private final int code;

    SelectMode(int code) {
        this.code = code;
    }


    public int code() {
        return code;
    }
}
