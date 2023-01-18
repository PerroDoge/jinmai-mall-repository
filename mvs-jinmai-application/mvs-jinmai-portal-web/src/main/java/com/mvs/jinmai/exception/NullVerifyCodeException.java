package com.mvs.jinmai.exception;

import com.baomidou.kaptcha.exception.KaptchaException;

public class NullVerifyCodeException extends KaptchaException {
    private String msg;

    public NullVerifyCodeException() {
        msg = "";
    }
    public NullVerifyCodeException(String msg) {
        this.msg = msg;
    }
}
