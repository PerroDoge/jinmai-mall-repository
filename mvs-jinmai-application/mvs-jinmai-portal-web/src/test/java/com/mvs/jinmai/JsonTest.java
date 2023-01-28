package com.mvs.jinmai;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.mvs.jinmai.getandpost.SignDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JsonTest {

    @Test
    public void toJson() {
        SignDTO signDTO = new SignDTO();
        signDTO.setAppId("1");
        signDTO.setName("ShouHe");
        signDTO.setSign("asduio");
        JSON json = JSONUtil.parse(signDTO);
        System.out.println(json);
    }
}
