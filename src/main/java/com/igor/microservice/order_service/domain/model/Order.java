package com.igor.microservice.order_service.domain.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "order_id")
  private UUID orderId;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price")
  private double price;

  public Order(String productName, Integer quantity, Double price) {
    this.productName = productName;
    this.quantity = quantity;
    this.price = price;
  }

  protected Order() {
    super();
  }
}
