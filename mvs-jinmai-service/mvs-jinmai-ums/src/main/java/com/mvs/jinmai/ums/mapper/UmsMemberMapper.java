package com.mvs.jinmai.ums.mapper;

import com.mvs.jinmai.entity.UmsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author ShouHe
 * @since 2023-01-05
 */
@Mapper
public interface UmsMemberMapper extends BaseMapper<UmsMember> {
    List<UmsMember> selectAll();

    UmsMember selectByUsername(UmsMember umsMember);

    int updateByUsername(UmsMember umsMember);
}
