package dev.anandraj.simplereactiveweb.repository;

import dev.anandraj.simplereactiveweb.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.math.BigInteger;
import java.util.UUID;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, BigInteger> {
    Flux<Order> findAllById(UUID id);
}