package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDTO {
    private Long id;
    private String username;
    private Long empId;
    private String empName;
    private String avatar;
}
