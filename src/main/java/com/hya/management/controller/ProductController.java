package com.hya.management.controller;


import com.hya.management.common.dto.ProductDTO;
import com.hya.management.common.dto.ProductQueryDTO;
import com.hya.management.common.vo.ProductVO;
import com.hya.management.service.ProductService;
import com.hya.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list/{current}/{size}")
    public Result list(@PathVariable Long current, @PathVariable Long size, ProductQueryDTO productQueryDTO) {
        return productService.productList(current, size, productQueryDTO);
    }

    @PreAuthorize("hasAuthority('warehouse')")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PreAuthorize("hasAuthority('warehouse')")
    @PutMapping("/update")
    public Result update(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @PreAuthorize("hasAuthority('warehouse')")
    @GetMapping("/listById/{id}")
    public Result listByWarehouseId(@PathVariable Long id) {
        return productService.productListByWarehouseId(id);
    }


}
