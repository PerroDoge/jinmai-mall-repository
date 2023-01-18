package com.mvs.jinmai.advise;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.mvs.jinmai.exception.NullVerifyCodeException;
import com.mvs.jinmai.exception.ValidateFailedException;
import com.mvs.jinmai.result.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {


    @ExceptionHandler(KaptchaException.class)
    public ResultWrapper kaptchaExceptionHandler(Exception e) {
        if (e instanceof NullVerifyCodeException) {
            return ResultWrapper.getFailBuilder().code(405).msg("请输入验证码").build();
        }else if (e instanceof ValidateFailedException) {
            return ResultWrapper.getFailBuilder().code(406).msg("验证失败，请重试").build();
        }
        return ResultWrapper.getFailBuilder().code(404).msg("验证码异常").build();
    }
}
