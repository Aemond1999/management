package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domain.RawMaterialDO;
import com.hya.management.common.dto.RawMaterialDTO;
import com.hya.management.common.dto.RawMaterialQueryDTO;
import com.hya.management.utils.Result;

public interface RawMaterialService extends IService<RawMaterialDO> {
    Result rawMaterialList(Long current, Long size, RawMaterialQueryDTO rawMaterialQueryDTO);
    Result updateRawMaterial(RawMaterialDTO rawMaterialDTO);
    Result deleteRawMaterial(Long id);
    Result rawMaterialListByWarehouseId(Long id);
}
