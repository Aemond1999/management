package com.hya.management.controller;

import com.hya.management.common.dto.CustomerDTO;
import com.hya.management.service.CustomerService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    Result list() {
        return customerService.customerList();
    }
    @PreAuthorize("hasAuthority('customer')")
    @PutMapping("/update")
    Result update(@RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }
    @PreAuthorize("hasAuthority('customer')")
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
    @PreAuthorize("hasAuthority('customer')")
    @PostMapping("/add")
    Result add(@RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }
}
