package com.hya.management.common.bo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class UserTokenBO {
    @TableId
    private Long id;
    private String username;
    private Long empId;
    private String empName;
    private String avatar;
}
