package com.renish.inventoryservice.Service;

import com.renish.inventoryservice.Repository.InventoryRepo;
import com.renish.inventoryservice.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class InventoryService {
	
    private final InventoryRepo inventoryRepo;
    
    @Transactional(readOnly = true)
    @SneakyThrows	
    public List<InventoryResponse> IsinStock(List<String> skuCode){
    	log.info("wait started");
    	Thread.sleep(10000);
    	log.info("wait ended");
        return inventoryRepo.findBySkuCodeIn(skuCode).stream()
        		.map(inventory ->
        			InventoryResponse.builder().skuCode(inventory.getSkuCode())
        			.isInStock(inventory.getQuantity()>0)
        			.build()
        		).toList();
      }
}
	