package com.hya.management.controller;

import com.hya.management.common.dto.SaleOrderDTO;
import com.hya.management.service.SaleOrderService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/saleOrder")
public class SaleOrderController {
    @Autowired
    private SaleOrderService saleOrderService;

    @PostMapping("/add")
    Result add(@RequestBody SaleOrderDTO saleOrderDTO){
        System.out.println(saleOrderDTO);
        return saleOrderService.addSaleOrder(saleOrderDTO);
    }
}
