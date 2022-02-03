package dev.anandraj.simplereactiveweb.service;

import dev.anandraj.simplereactiveweb.model.Order;
import dev.anandraj.simplereactiveweb.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Mono<Order> create(Order order) {
        return orderRepository.insert(order);
    }

    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
