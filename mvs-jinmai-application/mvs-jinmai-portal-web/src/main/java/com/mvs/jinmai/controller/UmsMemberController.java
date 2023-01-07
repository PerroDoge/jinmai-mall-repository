package com.mvs.jinmai.controller;

import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.entity.dto.UmsMemberLoginDTO;
import com.mvs.jinmai.entity.dto.UmsMemberRegisterDTO;
import com.mvs.jinmai.feignClient.UmsFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/register")
    @ResponseBody
    public int register(@RequestBody UmsMemberRegisterDTO umsMemberRegisterDTO) {
        System.out.println(umsMemberRegisterDTO);
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterDTO, umsMember);
        return umsFeignClient.register(umsMember);
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UmsMemberLoginDTO umsMemberLoginDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberLoginDTO, umsMember);
        UmsMember result = umsFeignClient.login(umsMember);
        if(null == result) {
            return "用户或密码错误";
        }
        return "登录成功";
    }
}
