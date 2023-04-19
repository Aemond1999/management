package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.domian.DeptDO;
import com.hya.management.common.vo.DeptVO;
import com.hya.management.utils.Result;

public interface DeptService extends IService<DeptDO> {


    Result deptList();
    DeptVO getDeptById(Long id);
}
