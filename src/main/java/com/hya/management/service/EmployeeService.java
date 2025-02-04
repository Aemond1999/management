package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domain.EmployeeDO;
import com.hya.management.common.dto.EmployeeDTO;
import com.hya.management.utils.Result;

public interface EmployeeService extends IService<EmployeeDO> {
    Result employeeList();
    Result updateEmployee(EmployeeDTO employeeDTO);
    Result deleteEmployee(Long id);

    Result addEmployee(EmployeeDTO employeeDTO);

    String selectEmpName(Long id);
}
