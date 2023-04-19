package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domian.CustomerDO;
import com.hya.management.common.dto.CustomerDTO;
import com.hya.management.utils.Result;

public interface CustomerService extends IService<CustomerDO> {

    Result customerList();
    Result updateCustomer(CustomerDTO customerDTO);
    Result deleteCustomer(Long id);

    Result addCustomer(  CustomerDTO customerDTO);
}
