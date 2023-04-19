package com.hya.management.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProviderDTO {

    private Long id;

    private String provName;

    private String linkman;

    private Long phone;

    private String address;

    private String bankName;

    private Long bankCard;
}
