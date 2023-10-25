package com.renish.inventoryservice.Controller;

import com.renish.inventoryservice.Service.InventoryService;
import com.renish.inventoryservice.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
	
    private  final InventoryService inventoryService;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode ){
        return inventoryService.IsinStock(skuCode);
    }
}
