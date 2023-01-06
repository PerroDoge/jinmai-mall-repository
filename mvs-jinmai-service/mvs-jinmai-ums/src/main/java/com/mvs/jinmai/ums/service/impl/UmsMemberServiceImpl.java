package com.mvs.jinmai.ums.service.impl;

import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.ums.mapper.UmsMemberMapper;
import com.mvs.jinmai.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public List<UmsMember> selectAll() {
        return umsMemberMapper.selectAll();
    }

    @Override
    public int register(UmsMember umsMember) {
        return umsMemberMapper.insert(umsMember);
    }
}
