package com.mvs.jinmai.controller.studyCaptcha;

import com.mvs.jinmai.code.ImageCode;
import com.mvs.jinmai.result.ResultWrapper;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/happy")
public class HappyCaptcharController {

    @RequestMapping("/generator")
    public void generator(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response)
                .style(CaptchaStyle.ANIM)
                .build().finish();
    }

    @GetMapping("/verify")
    public ResultWrapper verify(String verifyCode, HttpServletRequest request) {
        boolean res = HappyCaptcha.verification(request, verifyCode, true);
        if (!res) {
            return ResultWrapper.getFailBuilder().code(503).msg("你验证码填错啦！").build();
        }
        HappyCaptcha.remove(request);
        return ResultWrapper.getSuccessBuilder().msg("好耶！验证通过！").build();
    }
}
