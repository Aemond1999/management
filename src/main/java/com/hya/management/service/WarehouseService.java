package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domain.WarehouseDO;
import com.hya.management.common.dto.WarehouseDTO;
import com.hya.management.utils.Result;

public interface WarehouseService extends IService<WarehouseDO> {
    Result warehouseList();
    Result updateWarehouse(WarehouseDTO warehouseDTO);
    Result deleteWarehouse(Long id);

    Result addWarehouse( WarehouseDTO warehouseDTO);
}
