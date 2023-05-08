package com.hya.management.controller;

import com.hya.management.common.dto.ProviderDTO;
import com.hya.management.common.dto.WarehouseDTO;
import com.hya.management.service.ProviderService;
import com.hya.management.service.WarehouseService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('warehouse')")
    @PutMapping("/update")
    Result update(@RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.updateWarehouse(warehouseDTO);
    }
    @PreAuthorize("hasAuthority('warehouse')")
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Long id) {
        return warehouseService.deleteWarehouse(id);
    }
    @PreAuthorize("hasAuthority('warehouse')")
    @PostMapping("/add")
    Result add(@RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.addWarehouse(warehouseDTO);
    }


}
