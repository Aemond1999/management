package com.hya.management.controller;

import com.hya.management.common.dto.IdDTO;
import com.hya.management.common.dto.PurchaseOrderDTO;
import com.hya.management.common.dto.PurchaseOrderQueryDTO;
import com.hya.management.common.dto.SaleOrderQueryDTO;
import com.hya.management.service.PurchaseOrderService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping("/add")
    Result add(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        return purchaseOrderService.addPurchaseOrder(purchaseOrderDTO);
    }

    @GetMapping("/list/{current}/{size}")
    Result list(@PathVariable Long current, @PathVariable Long size, PurchaseOrderQueryDTO query) {
        return purchaseOrderService.purchaseOrderList(current, size, query);

    }

    @PutMapping("/audit")
    Result audit(@RequestBody IdDTO id) {
        return purchaseOrderService.audit(id.getId());
    }

    @PostMapping("/purchase")
    Result purchase(@RequestBody IdDTO id) {
        return purchaseOrderService.purchase(id.getId());
    }
}
