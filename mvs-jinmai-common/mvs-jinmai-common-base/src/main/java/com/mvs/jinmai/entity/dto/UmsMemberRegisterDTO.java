package com.mvs.jinmai.entity.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UmsMemberRegisterDTO {
    private String username;

    private String password;


    private String icon;


    private String email;


    private String nickName;
}
