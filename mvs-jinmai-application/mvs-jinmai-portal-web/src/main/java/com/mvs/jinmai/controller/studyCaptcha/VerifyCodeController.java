package com.mvs.jinmai.controller.studyCaptcha;

import com.mvs.jinmai.code.ImageCode;
import com.mvs.jinmai.result.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/code")
public class VerifyCodeController {

    public String attrName;

    @RequestMapping("/generator")
    public void generator(HttpServletRequest request, HttpServletResponse response) {
        ImageCode imageCode = ImageCode.getInstance();
        String code = imageCode.getCode();
        request.getSession().setAttribute(attrName, code);
        ByteArrayInputStream image = imageCode.getImage();

        response.setContentType("image/jpeg");

        byte[] bytes = new byte[1024];
        try(ServletOutputStream out = response.getOutputStream()) {
            while (image.read(bytes) != -1) {
                out.write(bytes);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/verify")
    public ResultWrapper verify(String verifyCode, HttpServletRequest request) {
        Object code = request.getSession().getAttribute(attrName);
        if(null == verifyCode) {
            return ResultWrapper.getFailBuilder().code(502).msg("你验证码呢？").build();
        }else if (!verifyCode.equals(code)) {
            return ResultWrapper.getFailBuilder().code(503).msg("你验证码填错啦！").build();
        }
        return ResultWrapper.getSuccessBuilder().msg("好耶！验证通过！").build();
    }
}
