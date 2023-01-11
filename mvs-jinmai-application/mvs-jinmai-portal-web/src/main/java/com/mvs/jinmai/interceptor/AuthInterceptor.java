package com.mvs.jinmai.interceptor;

import com.mvs.jinmai.annotation.Token;
import com.mvs.jinmai.exception.TokenException;
import com.mvs.jinmai.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 像验证码图片之类的一些静态资源暂且先用这种方式进行放行
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        String accessToken = request.getHeader("access_token");
        System.out.println("[preHandle's handler]: " + handler);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Token.class)) {
            Token token = method.getAnnotation(Token.class);
            if (token.enable()) {
                if (StringUtils.isBlank(accessToken)) {
                    throw new TokenException("欸，我Token呢？");
                }
                try {
                    JwtUtil.parserToken(accessToken);
                } catch (Exception e){

                    throw new TokenException("Token异常，老实说你是不是偷偷改了？");
                }
            }
        }

        return true;
    }
}
