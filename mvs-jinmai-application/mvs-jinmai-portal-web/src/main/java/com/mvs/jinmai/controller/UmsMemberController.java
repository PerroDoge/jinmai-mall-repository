package com.mvs.jinmai.controller;

import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.feignClient.UmsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author ShouHe
 * @since 2023-01-05
 */
@Controller
@RequestMapping("/umsMember")
public class UmsMemberController {

    @Autowired
    UmsFeignClient umsFeignClient;

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<UmsMember> selectAll() {

        return umsFeignClient.selectAll();
    }

    @RequestMapping("/register")
    @ResponseBody
    public int register(UmsMember umsMember) {
        UmsMember umsMember1 = new UmsMember();
        umsMember1.setNickName("gouor");
        return umsFeignClient.register(umsMember1);
    }
}
