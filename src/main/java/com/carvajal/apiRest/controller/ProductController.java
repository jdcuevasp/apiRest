package com.carvajal.apiRest.controller;

import com.carvajal.apiRest.common.ApiResponse;
import com.carvajal.apiRest.dto.ProductDto;
import com.carvajal.apiRest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>>  getProducts(){
        List<ProductDto> products= productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        productService.createProduct(productDto);
        return new ResponseEntity<>(new ApiResponse(true, "Producto ha sido agregado"), HttpStatus.CREATED);
    }

    @PostMapping("/edit/{idProduct}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("idProduct") Long idProduct, @RequestBody ProductDto productDto){
        productService.updateProduct(idProduct, productDto);
        return new ResponseEntity<>(new ApiResponse(true, "Producto ha sido editado"), HttpStatus.OK);
    }
}
