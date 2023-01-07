package com.mvs.jinmai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mvs.jinmai.entity.UmsMember;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author ShouHe
 * @since 2023-01-05
 */
public interface UmsMemberService extends IService<UmsMember> {
    List<UmsMember> selectAll();

    int register(UmsMember umsMember);

    UmsMember selectByUserName(UmsMember umsMember);
}
