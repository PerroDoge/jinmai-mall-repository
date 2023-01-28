package com.mvs.jinmai.controller;

import com.mvs.jinmai.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("safeapi")
public class SafeApiController {


    @RequestMapping("hello")
    public String hello() {
        return "hello safe api";
    }

    @RequestMapping("test")
    public String test(String appid, String id, String name, String sign) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appid", appid);
        map.put("id", id);
        map.put("name", name);
        log.info("[INFO]: 进入到Controller,状态良好");
        String s = CheckUtil.generatorSign(map);
        if (s.equals(sign)) return "参数校验通过";
        else return "参数校验不通过";
    }
}
