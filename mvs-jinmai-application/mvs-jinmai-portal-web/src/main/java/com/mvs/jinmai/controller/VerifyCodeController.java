package com.mvs.jinmai.controller;

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

    @RequestMapping("/generator")
    public void generator(HttpServletResponse response) {
        ImageCode imageCode = ImageCode.getInstance();
        String code = imageCode.getCode();
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
    public String verify(String verifyCode, HttpServletRequest request) {

        return "";
    }
}
