package com.mvs.jinmai.filter;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.mvs.jinmai.exception.SignAuthErrorException;
import com.mvs.jinmai.result.ResultWrapper;
import com.mvs.jinmai.util.CheckUtil;
import com.mvs.jinmai.util.HttpParamsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SignAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 获取url上的参数
        Map<String, Object> allParams = HttpParamsUtil.getAllParams(request);

        String clientSign = (String) allParams.remove("sign");
        // 生成签名
        String serverSign = CheckUtil.generatorSign(allParams);
        if (CheckUtil.checkSign(serverSign, clientSign)) filterChain.doFilter(servletRequest, servletResponse);
        else {
            servletResponse.setCharacterEncoding("utf-8");
            servletResponse.setContentType("application/json;utf-8");
            PrintWriter writer = servletResponse.getWriter();
            writer.print(ResultWrapper.getFailBuilder().code(112).msg("校验不通过").build());
            writer.flush();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
