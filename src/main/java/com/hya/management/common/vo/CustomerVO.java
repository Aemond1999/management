package com.hya.management.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerVO {

    private Long id;
    private String customerName;
    private String linkman;
    private Long phone;
    private String address;
    private String bankName;
    private Long bankCard;
}
