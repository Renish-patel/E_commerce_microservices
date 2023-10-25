package com.renish.inventoryservice;

import com.renish.inventoryservice.Model.Inventory;
import com.renish.inventoryservice.Repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
		@Bean
		public CommandLineRunner loadData (InventoryRepo inventoryRepo){
			return args -> {
				Inventory inventory = new Inventory();
				inventory.setSkuCode("iphone13");
				System.out.println("its an iphone 13");
				inventory.setQuantity(100);

				Inventory inventory1 = new Inventory();
				inventory1.setSkuCode("iphone15");
				inventory1.setQuantity(0);

				inventoryRepo.save(inventory1);
				inventoryRepo.save(inventory);
			};
		}
	}