package com.mvs.jinmai.code;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaRenderException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.mvs.jinmai.exception.ValidateFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class MyKaptcha implements Kaptcha {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DefaultKaptcha kaptcha;

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;


    @Override
    public String render() {
        String id = request.getSession().getId();
        this.response.setDateHeader("Expires", 0L);
        this.response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        this.response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        this.response.setHeader("Pragma", "no-cache");
        this.response.setContentType("image/jpeg");

        try {
            ServletOutputStream out = this.response.getOutputStream();
            Throwable var3 = null;

            String var4;
            try {
                String sessionCode = kaptcha.createText();
                ImageIO.write(this.kaptcha.createImage(sessionCode), "jpg", out);
                stringRedisTemplate.opsForValue().set(id, sessionCode);
                var4 = sessionCode;
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (out != null) {
                    if (var3 != null) {
                        try {
                            out.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        out.close();
                    }
                }

            }

            return var4;
        } catch (IOException var16) {
            throw new KaptchaRenderException(var16);
        }
    }

    @Override
    public boolean validate(String s) {
        String sessionID = request.getSession().getId();
        String code = stringRedisTemplate.opsForValue().get(sessionID);
        System.out.println("session提取完毕" + code);
        if (!s.equals(code)) {
            throw new ValidateFailedException();
        }
        return true;
    }

    @Override
    public boolean validate(String s, long l) {
        return false;
    }
}
