package com.school.design.automobile_sales_mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "tb_task_accept")
@Accessors(chain = true)
public class TaskAccept {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 任务id
    private Integer taskId;
    // 任务承接人
    private Integer acceptUserId;
    // 任务承接时间
    private String acceptTime;
    // 任务完成时间
    private String completeTime;
    // 任务是否完成
    private Integer isComplete;
    // 任务是否作废
    private Integer enable;
}
