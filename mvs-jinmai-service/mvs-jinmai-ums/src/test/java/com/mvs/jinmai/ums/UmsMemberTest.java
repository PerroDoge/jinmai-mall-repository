package com.mvs.jinmai.ums;

import com.mvs.jinmai.ums.entity.UmsMember;
import com.mvs.jinmai.ums.mapper.UmsMemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UmsMemberTest {

    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Test
    public void insertTest() {
        UmsMember member = new UmsMember();
        member.setUsername("ALang");
        member.setPassword("Doge1586288");
        member.setEmail("15826872@qq.com");
        umsMemberMapper.insert(member);
    }
}
