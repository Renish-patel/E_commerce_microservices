package com.renish.order.Service;

import com.renish.order.Dto.InventoryResponse;
import com.renish.order.Dto.OrderLineItemsDto;
import com.renish.order.Dto.OrderRequest;
import com.renish.order.Model.Order;
import com.renish.order.Model.OrderLineItems;
import com.renish.order.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final WebClient.Builder	 webClientBuilder;

	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto)
				.toList();
		order.setOrderLineItemsList(orderLineItems);
			
		List<String> skuCodes=order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
		
		// call inventoy service to see wheather the product is in stock or not
		
		InventoryResponse[] inventoryResponseArray = webClientBuilder.build()
				.get()
				.uri("http://INVENTORY-SERVICE/inventory",
				UriBuilder -> UriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve().bodyToMono(InventoryResponse[].class)
				.block();
			
			boolean allProdutsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::getIsInStock);
		if(allProdutsInStock) {
			orderRepository.save(order);
			return "order placed successfully";
		}
		else {
			throw new IllegalArgumentException("product is not in the inventory"); 
		}
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
