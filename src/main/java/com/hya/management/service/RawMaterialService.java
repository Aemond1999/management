package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domian.RawMaterialDO;
import com.hya.management.common.dto.ProductDTO;
import com.hya.management.common.dto.ProductQueryDTO;
import com.hya.management.common.dto.RawMaterialDTO;
import com.hya.management.common.dto.RawMaterialQueryDTO;
import com.hya.management.utils.Result;

public interface RawMaterialService extends IService<RawMaterialDO> {
    Result rawMaterialList(Long current, Long size, RawMaterialQueryDTO rawMaterialQueryDTO);
    Result updateRawMaterial(RawMaterialDTO rawMaterialDTO);
    Result deleteRawMaterial(Long id);
}
