package com.school.design.automobile_sales_mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import sun.reflect.annotation.TypeAnnotation;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "tb_task_publish")
public class TaskPublish {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //任务名称
    private String taskName;

    //任务描述
    private String taskDesc;

    //佣金
    private BigDecimal charge;

    //发布人
    private Integer publishUserId;

    //发布时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private String publishTime;

    //关闭时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private String closeTime;

    //是否被承接
    private Integer isAccept;

    private String username;

    //是否完成
    private Integer isComplete;

    //是否删除
    private Integer enable;
}
