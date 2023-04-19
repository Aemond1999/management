package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {

    private Long id;

    private String empName;

    private String gender;

    private String address;

    private Long phone;

    private LocalDateTime hireDate;

    private Long deptId;

    private String deptName;
}
