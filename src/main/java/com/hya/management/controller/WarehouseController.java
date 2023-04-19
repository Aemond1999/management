package com.hya.management.controller;

import com.hya.management.common.dto.ProviderDTO;
import com.hya.management.common.dto.WarehouseDTO;
import com.hya.management.service.ProviderService;
import com.hya.management.service.WarehouseService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/list")
    Result list() {
        return warehouseService.warehouseList();
    }

    @PutMapping("/update")
    Result update(@RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.updateWarehouse(warehouseDTO);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Long id) {
        return warehouseService.deleteWarehouse(id);
    }

    @PostMapping("/add")
    Result add(@RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.addWarehouse(warehouseDTO);
    }
}
