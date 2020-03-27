package com.school.design.automobile_sales_mall.response;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.school.design.automobile_sales_mall.enums.ErrorMsg;
import lombok.Data;

@Data
public class RespResult<T> {
    // 状态码
    private Integer code;
    // 返回信息
    private String msg;
    // 消息体
    private T data;

    public RespResult(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    public RespResult(String msg) {
        this.code = 400;
        this.msg = msg;
    }

    public RespResult(ErrorMsg errorMsg) {
        this.code = errorMsg.getCode();
        this.msg = errorMsg.getMsg();
    }

    public static RespResult success(Object data) {
        return new RespResult(data);
    }

    public static RespResult error(String msg) {
        return new RespResult(msg);
    }

    public static RespResult error(ErrorMsg errorMsg) {
        return new RespResult(errorMsg);
    }
}
