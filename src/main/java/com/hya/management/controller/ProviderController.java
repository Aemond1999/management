package com.hya.management.controller;

import com.hya.management.common.dto.CustomerDTO;
import com.hya.management.common.dto.ProviderDTO;
import com.hya.management.service.CustomerService;
import com.hya.management.service.ProviderService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/provider")

public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @GetMapping("/list")
    Result list() {
        return providerService.providerList();
    }

    @PreAuthorize("hasAuthority('provider')")
    @PutMapping("/update")
    Result update(@RequestBody ProviderDTO providerDTO) {
        return providerService.updateProvider(providerDTO);
    }

    @PreAuthorize("hasAuthority('provider')")
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Long id) {
        return providerService.deleteProvider(id);
    }

    @PreAuthorize("hasAuthority('provider')")
    @PostMapping("/add")
    Result add(@RequestBody ProviderDTO providerDTO) {
        return providerService.addProvider(providerDTO);
    }
}
