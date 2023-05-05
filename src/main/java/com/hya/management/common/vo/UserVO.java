package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class UserVO {
    private Long id;
    private String username;
    private String password;
    private Boolean status;
    private Long empId;
    private String empName;
    private Long roleId;
    private String roleName;
}
