package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeptVO {
    private Long id;
    private String deptName;
}
