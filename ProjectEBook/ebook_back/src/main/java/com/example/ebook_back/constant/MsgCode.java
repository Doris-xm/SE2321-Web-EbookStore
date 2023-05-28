package com.example.ebook_back.constant;

public enum MsgCode {
    SUCCESS(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG),
    ERROR(MsgUtil.ERROR,MsgUtil.ERROR_MSG),
    NOT_LOGGED_IN_ERROR(MsgUtil.NOT_LOGGED_IN_ERROR,MsgUtil.NOT_LOGGED_IN_ERROR_MSG);

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    private MsgCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
