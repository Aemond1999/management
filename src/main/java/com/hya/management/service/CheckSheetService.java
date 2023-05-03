package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domain.CheckSheetDO;
import com.hya.management.common.dto.CheckSheetDTO;
import com.hya.management.common.dto.CheckSheetQueryDTO;
import com.hya.management.utils.Result;

public interface CheckSheetService  extends IService<CheckSheetDO> {

    Result addCheckSheet(CheckSheetDTO checkSheetDTO);

    Result checkSheetList(Long current, Long size, CheckSheetQueryDTO query);

    Result audit(Long id);

    Result adjust(Long id);
}
