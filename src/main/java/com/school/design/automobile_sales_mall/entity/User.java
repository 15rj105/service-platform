package com.school.design.automobile_sales_mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "tb_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String stuNo;

    private String username;

    private String password;

    private String phone;

    private String email;
//
//    private Integer age;
//
    @TableField(exist = false)
    private String role = "普通用户";
}
