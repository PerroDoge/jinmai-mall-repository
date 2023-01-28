package com.mvs.jinmai.getandpost;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gap")
public class GAPController {


    @PostMapping("post-test")
    public String postTest(@RequestBody SignDTO signDTO) {



        return null;
    }
}
