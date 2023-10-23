package com.renish.inventoryservice.Service;

import com.renish.inventoryservice.Repository.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InventoryService {


    private final InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    public boolean IsinStock(String skuCode){
        return inventoryRepo.findBySkuCode().isPresent();

    }
}
