package com.igor.microservice.order_service.application.service.impl;

import com.igor.microservice.order_service.application.dto.OrderDTO;
import com.igor.microservice.order_service.application.service.OrderProducer;
import com.igor.microservice.order_service.domain.model.Order;
import java.util.UUID;

import com.igor.microservice.order_service.domain.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GerenciadorOrderProducer implements OrderProducer {

  private final KafkaTemplate<UUID, Order> kafkaTemplate;
  private final OrderRepository orderRepository;

  private final String TOPIC_NAME = "order-topico";

  public GerenciadorOrderProducer(
      KafkaTemplate<UUID, Order> kafkaTemplate, OrderRepository orderRepository) {
    this.kafkaTemplate = kafkaTemplate;
    this.orderRepository = orderRepository;
  }

  @Transactional
  @Override
  public OrderDTO sendOrder(OrderDTO orderDTO) {

    Order order = new Order(orderDTO.getProductName(), orderDTO.getQuantity(), orderDTO.getPrice());

    orderRepository.save(order);
    kafkaTemplate.send(TOPIC_NAME, order.getOrderId(), order);
    return OrderDTO.map(order);
  }
}
