package com.school.design.automobile_sales_mall.enums;

import lombok.Getter;

@Getter
public enum ErrorMsg implements ErrorCode {
    NOT_LOGIN(1001, "您还未登录，请先进行登录");

    private Integer code;
    private String msg;

    ErrorMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public Integer getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.msg;
    }

    @Override
    public String toString() {
        return String.format("Code:[%s], Message:[%s]", this.code, this.msg);
    }
}
