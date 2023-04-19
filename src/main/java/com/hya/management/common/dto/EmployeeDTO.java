package com.hya.management.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String empName;

    private String gender;

    private String address;

    private Long phone;

    private LocalDateTime hireDate;

    private Long deptId;


}
