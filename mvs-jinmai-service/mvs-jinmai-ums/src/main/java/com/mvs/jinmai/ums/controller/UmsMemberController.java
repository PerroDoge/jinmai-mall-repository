package com.mvs.jinmai.ums.controller;

import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.result.ResultWrapper;
import com.mvs.jinmai.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
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
    UmsMemberService umsMemberService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public ResultWrapper<List<UmsMember>> selectAll() {

        return umsMemberService.selectAll();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultWrapper register(@RequestBody UmsMember umsMember) {
        System.out.println("Hello Controller!");
        return umsMemberService.register(umsMember);
    }

    @RequestMapping("/selectByUsername")
    @ResponseBody
    public ResultWrapper selectByUsername(@RequestBody UmsMember umsMember) {
        return umsMemberService.selectByUserName(umsMember);
    }

    @RequestMapping("/updateByUsername")
    @ResponseBody
    public ResultWrapper updateByUsername(@RequestBody UmsMember umsMember) {
        System.out.println("[updateByUsername]:" + umsMember.getUsername());
        return umsMemberService.updateByUsername(umsMember);
    }
}
