package com.mvs.jinmai.entity.response;

import com.mvs.jinmai.entity.UmsMember;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UmsMemberLoginResponse {

    private String accessToken;
    private String refreshToken;
    private UmsMember umsMember;
}
