package com.hya.management.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProviderVO {
    private Long id;

    private String provName;

    private String linkman;

    private Long phone;

    private String address;

    private String bankName;

    private Long bankCard;
}
