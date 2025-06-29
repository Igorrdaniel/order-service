package com.igor.microservice.order_service.application.api;

import com.igor.microservice.order_service.application.dto.OrderDTO;
import com.igor.microservice.order_service.application.service.impl.GerenciadorOrderProducer;
import com.igor.microservice.order_service.domain.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final GerenciadorOrderProducer gerenciadorOrderProducer;

  public OrderController(GerenciadorOrderProducer gerenciadorOrderProducer) {
    this.gerenciadorOrderProducer = gerenciadorOrderProducer;
  }

  @PostMapping
  @Operation(
      summary = "Cria um  novo pedido no sistema",
      description = "Cria um novo pedido no sistema com as informações especificadas.",
      responses = {
        @ApiResponse(responseCode = "201", description = "pedido incluido com sucesso"),
      })
  public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
    return gerenciadorOrderProducer.sendOrder(orderDTO);
  }
}
