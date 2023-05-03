package com.hya.management.controller;

import com.hya.management.common.dto.CustomerDTO;
import com.hya.management.common.dto.EmployeeDTO;
import com.hya.management.service.EmployeeService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    Result list() {
        return employeeService.employeeList();
    }

    @PreAuthorize("hasAuthority('employee')")
    @PutMapping("/update")
    Result update(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }

    @PreAuthorize("hasAuthority('employee')")
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/add")
    Result add(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

}
