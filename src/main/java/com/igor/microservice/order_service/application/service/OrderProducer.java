package com.igor.microservice.order_service.application.service;

import com.igor.microservice.order_service.application.dto.OrderDTO;

public interface OrderProducer {

  OrderDTO sendOrder(OrderDTO orderDTO);
}
