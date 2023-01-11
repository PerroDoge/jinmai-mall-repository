package com.mvs.jinmai.ums.service.impl;

import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.entity.response.UmsMemberLoginResponse;
import com.mvs.jinmai.result.ResultWrapper;
import com.mvs.jinmai.ums.mapper.UmsMemberMapper;
import com.mvs.jinmai.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvs.jinmai.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author ShouHe
 * @since 2023-01-05
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public ResultWrapper<List<UmsMember>> selectAll() {
        List<UmsMember> res = umsMemberMapper.selectAll();
        return ResultWrapper.getSuccessBuilder().data(res).build();
    }

    @Override
    public ResultWrapper register(UmsMember umsMember) {
        String encode = passwordEncoder.encode(umsMember.getPassword());
        umsMember.setPassword(encode);
        int res = umsMemberMapper.insert(umsMember);
        if (res == 0) return ResultWrapper.getFailBuilder().code(101).msg("注册失败").build();
        else return ResultWrapper.getSuccessBuilder().msg("注册成功").build();
    }

    @Override
    public ResultWrapper selectByUserName(UmsMember umsMember) {
        UmsMember result = umsMemberMapper.selectByUsername(umsMember);

        if( null != result) {
            if (!passwordEncoder.matches(umsMember.getPassword(), result.getPassword())) {
                return ResultWrapper.getFailBuilder().code(103).msg("密码错误").build();
            }
        }else {
            return ResultWrapper.getFailBuilder().code(102).msg("用户不存在").build();
        }

        String accessToken = JwtUtil.createToken(result.getUsername(), 1000 * 60 * 30);
        UmsMemberLoginResponse response = new UmsMemberLoginResponse();
        response.setUmsMember(result);
        response.setAccessToken(accessToken);
        return ResultWrapper.getSuccessBuilder().msg("登录成功").data(response).build();
    }

    @Override
    public ResultWrapper updateByUsername(UmsMember umsMember) {
        int i = umsMemberMapper.updateByUsername(umsMember);
        if (i == 0) {
            return ResultWrapper.getFailBuilder().code(104).msg("修改失败！").build();
        }
        return ResultWrapper.getSuccessBuilder().msg("修改成功~").build();
    }
}
