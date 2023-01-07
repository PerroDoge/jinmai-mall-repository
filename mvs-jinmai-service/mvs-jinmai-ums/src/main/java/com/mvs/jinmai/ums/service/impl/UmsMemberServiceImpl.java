package com.mvs.jinmai.ums.service.impl;

import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.ums.mapper.UmsMemberMapper;
import com.mvs.jinmai.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public List<UmsMember> selectAll() {
        return umsMemberMapper.selectAll();
    }

    @Override
    public int register(UmsMember umsMember) {
        String encode = passwordEncoder.encode(umsMember.getPassword());
        umsMember.setPassword(encode);
        System.out.println("[password encoded]: " + encode);
        return umsMemberMapper.insert(umsMember);
    }

    @Override
    public UmsMember selectByUserName(UmsMember umsMember) {
        UmsMember result = umsMemberMapper.selectByUsername(umsMember);

        if( null != result) {
            if (!passwordEncoder.matches(umsMember.getPassword(), result.getPassword())) {
                System.out.println("密码错误");
                return null;
            }
        }else {
            System.out.println("用户不存在");
            return null;
        }
        return result;
    }
}
