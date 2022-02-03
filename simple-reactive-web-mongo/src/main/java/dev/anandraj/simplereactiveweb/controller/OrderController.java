package dev.anandraj.simplereactiveweb.controller;

import dev.anandraj.simplereactiveweb.model.Order;
import dev.anandraj.simplereactiveweb.service.OrderService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("orders")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        return orderService.create(order);
    }

    @GetMapping
    public Flux<Order> getOrders() {
        return orderService.getAllOrders();
    }
}
