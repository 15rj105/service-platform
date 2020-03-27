package com.school.design.automobile_sales_mall.entity.dto;

import lombok.Data;

@Data
public class TaskAcceptDto {
    private Integer id;

    private String taskName;

    private String taskDesc;

    private String publishTime;

    private String acceptTime;

    private String publishUser;

    private Double charge;
}
