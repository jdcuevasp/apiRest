package com.carvajal.apiRest.service;

import com.carvajal.apiRest.dto.ProductDto;
import com.carvajal.apiRest.model.Product;
import com.carvajal.apiRest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setStockQuantity(product.getStockQuantity());
        productDto.setDescription(product.getDescription());
        return  productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(Long idProduct,ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(idProduct);

        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
    }
}
