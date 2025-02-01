package com.krishnendu.microservices.order.Controller;

import com.krishnendu.microservices.order.Repository.OrderRepository;
import com.krishnendu.microservices.order.Service.OrderService;
import com.krishnendu.microservices.order.dto.OrderRequest;
import com.krishnendu.microservices.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
    @GetMapping
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
}
