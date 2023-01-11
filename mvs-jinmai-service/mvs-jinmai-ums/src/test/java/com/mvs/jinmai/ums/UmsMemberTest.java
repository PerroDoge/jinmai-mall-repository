package com.mvs.jinmai.ums;

import com.mvs.jinmai.entity.UmsMember;
import com.mvs.jinmai.result.ResultWrapper;
import com.mvs.jinmai.service.UmsMemberService;
import com.mvs.jinmai.ums.mapper.UmsMemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UmsMemberTest {

    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Autowired
    UmsMemberService umsMemberService;
    @Test
    public void insertTest() {
        UmsMember member = new UmsMember();
        member.setUsername("ALang");
        member.setPassword("Doge1586288");
        member.setEmail("15826872@qq.com");
        umsMemberMapper.insert(member);
    }

    @Test
    public void selectAllTest() {
        ResultWrapper<List<UmsMember>> res = umsMemberService.selectAll();
        System.out.println(res.getData());
    }
}
