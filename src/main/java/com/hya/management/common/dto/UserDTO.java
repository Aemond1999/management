package com.hya.management.common.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Boolean status;
    private Long empId;
    private Long roleId;

}
