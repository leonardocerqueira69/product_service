package com.microservices.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.product.dto.ProductDto;
import com.microservices.product.dto.ProductMapperDto;
import com.microservices.product.entities.Product;
import com.microservices.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductDto createProduct (ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.name())
                .description(productDto.description())
                .price(productDto.price())
                .build();
        Product savedProduct = productRepository.save(product);
        return ProductMapperDto.maptoDto(savedProduct);
    }


    public List <ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapperDto::maptoDto)
                .toList();
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public ProductDto getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow();
        return ProductMapperDto.maptoDto(product);
    }

    public ProductDto updateProduct(String id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        Product updatedProduct = productRepository.save(product);
        return ProductMapperDto.maptoDto(updatedProduct);
    }
}
