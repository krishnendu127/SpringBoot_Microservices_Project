package com.krishnendu.microservices.order.Repository;

import com.krishnendu.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
