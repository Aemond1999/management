package com.hya.management.controller;

import com.hya.management.common.dto.*;
import com.hya.management.service.CheckSheetService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/checkSheet")
public class CheckSheetController {
    @Autowired
    private CheckSheetService checkSheetService;

    @PreAuthorize("hasAuthority('check')")
    @PostMapping("/add")
    Result add(@RequestBody CheckSheetDTO checkSheetDTO) {
        return checkSheetService.addCheckSheet(checkSheetDTO);
    }

    @GetMapping("/list/{current}/{size}")
    Result list(@PathVariable Long current, @PathVariable Long size, CheckSheetQueryDTO query) {
        return checkSheetService.checkSheetList(current, size, query);
    }

    @PreAuthorize("hasAuthority('check')")
    @PutMapping("/audit")
    Result audit(@RequestBody IdDTO id) {
        return checkSheetService.audit(id.getId());
    }

    @PreAuthorize("hasAuthority('check')")
    @PostMapping("/adjust")
    Result adjust(@RequestBody IdDTO id) {
        return checkSheetService.adjust(id.getId());
    }
}
