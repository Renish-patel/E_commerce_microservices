package com.renish.inventoryservice.Service;

import com.renish.inventoryservice.Repository.InventoryRepo;
import com.renish.inventoryservice.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InventoryService {
	
    private final InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    public List<InventoryResponse> IsinStock(List<String> skuCode){
        return inventoryRepo.findBySkuCodeIn(skuCode).stream()
        		.map(inventory ->
        			InventoryResponse.builder().skuCode(inventory.getSkuCode())
        			.isInStock(inventory.getQuantity()>0)
        			.build()
        		).toList();
      }
}
	