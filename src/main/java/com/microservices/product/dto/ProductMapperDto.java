package com.microservices.product.dto;

import com.microservices.product.entities.Product;

public class ProductMapperDto {
    public static ProductDto maptoDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product maptoEntity(ProductDto dto) {
        return Product.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .build();
    }
}
