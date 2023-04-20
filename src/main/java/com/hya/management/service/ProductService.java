package com.hya.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hya.management.common.bo.ProductBO;
import com.hya.management.common.domian.ProductDO;
import com.hya.management.common.dto.ProductDTO;
import com.hya.management.common.dto.ProductQueryDTO;
import com.hya.management.common.dto.ProviderDTO;
import com.hya.management.utils.Result;

import java.util.List;

public interface ProductService extends IService<ProductDO> {
    Result productList(Long current, Long size, ProductQueryDTO productQueryDTO);
    Result updateProduct(ProductDTO productDTO);
    Result deleteProduct(Long id);

    List<ProductBO> productListByWarehouseId(Long id);
}
