package com.krishnendu.microservices.order.Service;

import com.krishnendu.microservices.order.Client.InventoryClient;
import com.krishnendu.microservices.order.Event.OrderPlacedEvent;
import com.krishnendu.microservices.order.Repository.OrderRepository;
import com.krishnendu.microservices.order.dto.OrderRequest;
import com.krishnendu.microservices.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

import java.util.UUID;
import java.util.logging.Logger;



@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock=inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(isProductInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());
            orderRepository.save(order);
            Logger log = null;

            //Send message to kafka topic
            //order number,email
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequest.userDetails().email());


            log.info("Start-Order placed successfully");
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End-Order placed successfully");
        }
        else{
            throw new RuntimeException("Product with SkuCode "+ orderRequest.skuCode()+ " is not in stock");
        }
    }
}
