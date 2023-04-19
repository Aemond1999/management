package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domian.EmployeeDO;
import com.hya.management.common.dto.CustomerDTO;
import com.hya.management.common.dto.EmployeeDTO;
import com.hya.management.utils.Result;

public interface EmployeeService extends IService<EmployeeDO> {
    Result employeeList();
    Result updateEmployee(EmployeeDTO employeeDTO);
    Result deleteEmployee(Long id);

    Result addEmployee(EmployeeDTO employeeDTO);
}
