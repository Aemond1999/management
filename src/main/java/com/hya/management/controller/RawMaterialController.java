package com.hya.management.controller;


import com.hya.management.common.dto.RawMaterialDTO;
import com.hya.management.common.dto.RawMaterialQueryDTO;
import com.hya.management.service.RawMaterialService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rawMaterial")
public class RawMaterialController {
    @Autowired
    private RawMaterialService rawMaterialService;

    @GetMapping("/list/{current}/{size}")
    public Result list(@PathVariable Long current, @PathVariable Long size, RawMaterialQueryDTO rawMaterialQueryDTO) {
        return rawMaterialService.rawMaterialList(current, size, rawMaterialQueryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return rawMaterialService.deleteRawMaterial(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody RawMaterialDTO rawMaterialDTO) {
        return rawMaterialService.updateRawMaterial(rawMaterialDTO);

    }
}
