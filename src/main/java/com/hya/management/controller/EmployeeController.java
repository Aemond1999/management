package com.hya.management.controller;

import com.hya.management.common.dto.CustomerDTO;
import com.hya.management.common.dto.EmployeeDTO;
import com.hya.management.service.EmployeeService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/update")
    Result update(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
    @PostMapping("/add")
    Result add(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addEmployee(employeeDTO);
    }

}
