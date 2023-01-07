package com.mvs.jinmai.controller;

import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.entity.dto.UmsMemberLoginDTO;
import com.mvs.jinmai.entity.dto.UmsMemberRegisterDTO;
import com.mvs.jinmai.feignClient.UmsFeignClient;
import com.mvs.jinmai.result.ResultWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
    public ResultWrapper<List<UmsMember>> selectAll() {
        List<UmsMember> res = umsFeignClient.selectAll();
        return ResultWrapper.getSuccessBuilder().data(res).build();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultWrapper<UmsMember> register(@RequestBody @Valid UmsMemberRegisterDTO umsMemberRegisterDTO) {
        System.out.println("[hello, I'm here!]: " + umsMemberRegisterDTO);
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterDTO, umsMember);
        int result = umsFeignClient.register(umsMember);
        if(result == 0) return ResultWrapper.getFailBuilder().msg("注册失败").build();
        return ResultWrapper.getSuccessBuilder().msg("注册成功").build();
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultWrapper<UmsMember> login(@RequestBody UmsMemberLoginDTO umsMemberLoginDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberLoginDTO, umsMember);
        UmsMember result = umsFeignClient.login(umsMember);
        if(null == result) {
            return ResultWrapper.getFailBuilder().msg("登录失败").build();
        }
        return ResultWrapper.getSuccessBuilder().msg("登录成功").data(result).build();
    }
}
