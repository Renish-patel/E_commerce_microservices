package com.renish.order.Controller;

import com.renish.order.Dto.OrderRequest;
import com.renish.order.Service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @ResponseStatus()
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    @PostMapping
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
    return  CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));  
    }
    public CompletableFuture<String>fallbackMethod(OrderRequest orderRequest , RuntimeException runtimeException) {
    	return CompletableFuture.supplyAsync(()->"opps Something went worng");
    }
}