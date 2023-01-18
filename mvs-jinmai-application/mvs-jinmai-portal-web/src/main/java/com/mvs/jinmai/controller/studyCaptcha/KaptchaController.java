package com.mvs.jinmai.controller.studyCaptcha;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.mvs.jinmai.code.MyKaptcha;
import com.mvs.jinmai.result.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    MyKaptcha kaptcha;

    @RequestMapping("/generator")
    public String generator(HttpServletRequest request) {
        return kaptcha.render();
    }

    @RequestMapping("/verify")
    public ResultWrapper verify(String verifyCode) {
        boolean validate = kaptcha.validate(verifyCode);
        if (!validate) {
            throw new KaptchaException();
        }
        return ResultWrapper.getSuccessBuilder().msg("验证成功!").build();
    }
}
