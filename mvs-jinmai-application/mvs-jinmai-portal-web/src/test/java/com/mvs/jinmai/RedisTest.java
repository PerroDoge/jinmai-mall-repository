package com.mvs.jinmai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest()
public class RedisTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void setTest() {
        redisTemplate.opsForValue().set("p1", "9999");
    }
}
