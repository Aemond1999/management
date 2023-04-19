package com.hya.management.common.domian;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@TableName("employee")
public class EmployeeDO {
    @TableId
    private Long id;
    @TableField("emp_name")
    private String empName;
    @TableField("gender")
    private String  gender;
    @TableField("address")
    private String address;
    @TableField("phone")
    private Long phone;
    @TableField("hire_date")
    private LocalDateTime hireDate;

    @TableField("dept_id")
    private Long deptId;
    @TableField("deleted")
    private Boolean deleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField("create_by")
    private Long createBy;
    @TableField(value = "update_time",fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField("update_by")
    private Long updateBy;
}
