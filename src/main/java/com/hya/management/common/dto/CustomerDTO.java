package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String customerName;
    private String linkman;
    private Long phone;
    private String address;
    private String bankName;
    private Long bankCard;
}
