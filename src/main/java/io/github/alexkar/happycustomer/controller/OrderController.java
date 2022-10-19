package io.github.alexkar.happycustomer.controller;

import io.github.alexkar.happycustomer.service.OrderRequestHandler;
import io.github.alexkar.happycustomer.dto.OrderRequestV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("v1")
public class OrderController extends BaseController {
    @Autowired
    private OrderRequestHandler orderRequestHandler;

    @PostMapping("/order")
    public void order(@Valid @RequestBody OrderRequestV1 request) {
        log.debug("Order [{}]", request);
        orderRequestHandler.handleOrderRequest(request);
    }

}
