package com.mvs.jinmai.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpParamsUtil {


    public static Map<String, Object> getAllParams(HttpServletRequest request) {
        Map<String, Object> allParams = new HashMap<>();
        Map<String, Object> urlParams = getUrlParams(request);
        Map<String, Object> bodyParams = getBodyParams(request);
        allParams.putAll(urlParams);
        allParams.putAll(bodyParams);

        return allParams;
    }

    private static Map<String, Object> getUrlParams(HttpServletRequest request) {
        Map<String, Object> paramsMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            // 转成map
            paramsMap.put(parameterName, request.getParameter(parameterName));
        }

        return paramsMap;
    }

    private static Map<String, Object> getBodyParams(HttpServletRequest request) {
        Map<String, Object> paramsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(request.getReader())) {
            StringBuffer sb = new StringBuffer();
            char[] buffer = new char[1024];

            int len;
            while ((len = br.read(buffer)) != -1) {
                sb.append(buffer, 0, len);
            }
            if (sb.length() == 0) return paramsMap;
            log.info(sb.toString());
            JSON parameter = JSONUtil.parse(sb.toString());
            // 转成map
            paramsMap.putAll(Convert.toMap(String.class, Object.class, parameter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return paramsMap;
    }
}
