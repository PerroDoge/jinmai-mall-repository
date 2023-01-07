package com.mvs.jinmai.feignClient;

import com.mvs.jinmai.entity.UmsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@FeignClient("mvs-jinmai-ums-provider")
public interface UmsFeignClient {


    @RequestMapping("/umsMember/selectAll")
    public List<UmsMember> selectAll();

    @PostMapping("/umsMember/register")
    int register(@RequestBody UmsMember umsMember);

    @RequestMapping("/umsMember/selectByUsername")
    UmsMember login(@RequestBody UmsMember umsMember);
}
