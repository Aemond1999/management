package com.hya.management.controller;

import com.hya.management.service.CatalogueService;
import com.hya.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/catalogue")
public class CatalogueController {
    @Autowired
   private CatalogueService catalogueService;
    @GetMapping("/listById/{id}")
    Result listByProvId(@PathVariable Long id){
        return catalogueService.catalogueListByProvId(id);
    }
}
