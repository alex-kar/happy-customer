package io.github.alexkar.happycustomer.controller;

import io.github.alexkar.happycustomer.service.SaleRequestHandler;
import io.github.alexkar.happycustomer.dto.SaleRequestV1;
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
public class SaleController extends BaseController {
    @Autowired
    private SaleRequestHandler saleRequestHandler;

    @PostMapping("/sale")
    public void sale(@Valid @RequestBody SaleRequestV1 request) {
        log.debug("Sale request [{}]", request);
        saleRequestHandler.handleSaleRequest(request);
    }

}
