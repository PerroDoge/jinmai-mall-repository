package com.mvs.jinmai.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ToString
@Data
public class UmsMemberRegisterDTO {
    @NotEmpty(message = "大哥，用户名怎么是空的啊？")
    private String username;

    private String password;

    private String icon;

    @Email
    private String email;


    private String nickName;
}
