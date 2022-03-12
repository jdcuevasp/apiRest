package com.carvajal.apiRest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private @NotNull String name;
    private @NotNull Double price;
    private @NotNull Long stockQuantity;
    private @NotNull String description;
}
