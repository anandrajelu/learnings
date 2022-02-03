package dev.anandraj.simplereactiveweb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Document
public record Order(@Id BigInteger id, List<Item> itemList, LocalDateTime requestedOn, User user) {
}

record Item(BigInteger id, String name, Integer quantity) {
}

record User(BigInteger id, String name) {
}
