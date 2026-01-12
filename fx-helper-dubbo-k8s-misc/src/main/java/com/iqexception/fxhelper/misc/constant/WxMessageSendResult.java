package com.iqexception.fxhelper.misc.constant;

public enum WxMessageSendResult {

    CREATED(0),
    SUCCEED(1),
    FAILED(-1);

    private final int val;

    WxMessageSendResult(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

}
