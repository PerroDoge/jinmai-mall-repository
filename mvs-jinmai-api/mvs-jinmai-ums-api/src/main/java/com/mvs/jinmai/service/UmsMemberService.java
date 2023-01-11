package com.mvs.jinmai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.result.ResultWrapper;

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
    ResultWrapper<List<UmsMember>> selectAll();

    ResultWrapper register(UmsMember umsMember);

    ResultWrapper selectByUserName(UmsMember umsMember);

    ResultWrapper updateByUsername(UmsMember umsMember);
}
