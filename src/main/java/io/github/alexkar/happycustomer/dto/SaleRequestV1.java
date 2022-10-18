package io.github.alexkar.happycustomer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SaleRequestV1 {
    @NotBlank(message = "Customer id is mandatory")
    private String customerId;
    @NotBlank(message = "Item id is mandatory")
    private String itemId;
    @Positive(message = "Price should be positive")
    @NotNull
    private Long price;
}
