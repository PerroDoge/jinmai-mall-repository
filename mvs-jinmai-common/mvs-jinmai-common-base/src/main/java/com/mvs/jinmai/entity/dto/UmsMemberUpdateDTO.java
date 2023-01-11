package com.mvs.jinmai.entity.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class UmsMemberUpdateDTO {

    private String username;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private LocalDateTime loginTime;

    private Integer status;
}
