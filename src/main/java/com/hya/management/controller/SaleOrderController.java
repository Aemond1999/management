package com.hya.management.controller;

import com.hya.management.common.dto.IdDTO;
import com.hya.management.common.dto.SaleOrderDTO;
import com.hya.management.common.dto.SaleOrderQueryDTO;
import com.hya.management.service.SaleOrderService;
import com.hya.management.utils.Result;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/saleOrder")
public class SaleOrderController {
    @Autowired
    private SaleOrderService saleOrderService;
    @GetMapping("/list/{current}/{size}")
    Result list(@PathVariable Long current, @PathVariable Long size, SaleOrderQueryDTO saleOrderQueryDTO){
        return saleOrderService.saleOrderList(current,size,saleOrderQueryDTO);

    }
    @PostMapping("/add")
    Result add(@RequestBody SaleOrderDTO saleOrderDTO){
        return saleOrderService.addSaleOrder(saleOrderDTO);
    }
    @PutMapping("/audit")
    Result audit(@RequestBody IdDTO id){

        return saleOrderService.audit(id.getId());
    }
    @PostMapping("/sale")
    Result  sale( @RequestBody IdDTO id){
        return saleOrderService.sale(id.getId());
    }
}
