package com.mvs.jinmai.result;

import com.mvs.jinmai.enums.StatusCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@ToString
@Builder
@Data
public class ResultWrapper<T> {

    private int code;
    private String msg;
    private T data;


    public static ResultWrapperBuilder getSuccessBuilder() {
        return new ResultWrapperBuilder<>().code(StatusCode.SUCCESS.getCode()).msg(StatusCode.SUCCESS.getMsg());
    }
    public static ResultWrapperBuilder getFailBuilder() {
        return new ResultWrapperBuilder<>().code(StatusCode.FAIL.getCode()).msg(StatusCode.FAIL.getMsg());
    }
}
