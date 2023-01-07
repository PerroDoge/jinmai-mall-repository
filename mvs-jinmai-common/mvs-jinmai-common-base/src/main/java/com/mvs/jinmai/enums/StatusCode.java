package com.mvs.jinmai.enums;

import lombok.Data;

public enum StatusCode {
    SUCCESS(200, "请求成功"), FAIL(100, "请求失败");


    private int code;
    private String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
