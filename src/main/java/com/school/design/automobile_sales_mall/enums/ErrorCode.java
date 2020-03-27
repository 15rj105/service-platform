package com.school.design.automobile_sales_mall.enums;

public interface ErrorCode extends IEnum<Integer, String> {
    default Integer getCode(){
        return (Integer) this.getKey();
    };

    default String getMsg() {
        return (String) this.getValue();
    };

    String toString();
}
