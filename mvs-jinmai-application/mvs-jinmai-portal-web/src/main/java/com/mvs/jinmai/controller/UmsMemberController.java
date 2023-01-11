package com.mvs.jinmai.controller;

import com.mvs.jinmai.annotation.Token;
import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.entity.dto.UmsMemberLoginDTO;
import com.mvs.jinmai.entity.dto.UmsMemberRegisterDTO;
import com.mvs.jinmai.entity.dto.UmsMemberUpdateDTO;
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

        return umsFeignClient.selectAll();
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultWrapper<UmsMember> register(@RequestBody @Valid UmsMemberRegisterDTO umsMemberRegisterDTO) {
        System.out.println("[hello, I'm here!]: " + umsMemberRegisterDTO);
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterDTO, umsMember);
        return umsFeignClient.register(umsMember);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultWrapper<UmsMember> login(@RequestBody UmsMemberLoginDTO umsMemberLoginDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberLoginDTO, umsMember);
        return umsFeignClient.login(umsMember);
    }

    @RequestMapping("/edit")
    @ResponseBody
    @Token
    public ResultWrapper edit(@RequestBody UmsMemberUpdateDTO umsMemberUpdateDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberUpdateDTO, umsMember);
        System.out.println("[edit]:" + umsMember.getUsername());
        return umsFeignClient.edit(umsMember);
    }
}
