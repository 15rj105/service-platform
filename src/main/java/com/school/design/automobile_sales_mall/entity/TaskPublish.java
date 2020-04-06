package com.school.design.automobile_sales_mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sun.reflect.annotation.TypeAnnotation;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel
@TableName(value = "tb_task_publish")
public class TaskPublish {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "任务id", name = "id", hidden = true)
    private Integer id;

    //任务名称
    @ApiModelProperty(value = "任务名称", name = "taskName")
    private String taskName;

    //任务描述
    @ApiModelProperty(value = "任务描述", name = "taskDesc")
    private String taskDesc;

    //佣金
    @ApiModelProperty(value = "佣金", name = "charge")
    private BigDecimal charge;

    //发布人
    @ApiModelProperty(value = "发布人Id", name = "publishUserId", hidden = true)
    private Integer publishUserId;

    //发布时间
    @ApiModelProperty(value = "任务发布时间", name = "publishTime", hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private String publishTime;

    //关闭时间
    @ApiModelProperty(value = "任务关闭时间", name = "closeTime", hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private String closeTime;

    //是否被承接
    @ApiModelProperty(value = "任务是否被承接", name = "isAccept", hidden = true)
    private Integer isAccept;

    @ApiModelProperty(value = "发布人姓名", name = "username", hidden = true)
    private String username;

    //是否完成
    @ApiModelProperty(value = "任务是否完成", name = "isComplete", hidden = true)
    private Integer isComplete;

    //是否删除
    @ApiModelProperty(value = "是否删除", name = "enable", hidden = true)
    private Integer enable;

    @ApiModelProperty(value = "任务类别(1-快递，2-外卖，3-失物招领)", name = "catalog")
    private Integer catalog;
}
