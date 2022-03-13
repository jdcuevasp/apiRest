package com.carvajal.apiRest.controller;

import com.carvajal.apiRest.common.ApiResponse;
import com.carvajal.apiRest.dto.ProductDto;
import com.carvajal.apiRest.model.Product;
import com.carvajal.apiRest.model.User;
import com.carvajal.apiRest.model.WishList;
import com.carvajal.apiRest.service.TokenService;
import com.carvajal.apiRest.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    TokenService tokenService;

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) throws Exception {
        tokenService.authenticate(token);

        User user = tokenService.getUser(token);

        List<ProductDto> productDtos = wishListService.getWishListForUser(user);

        return  new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token) throws Exception {
        tokenService.authenticate(token);

        User user = tokenService.getUser(token);

        WishList wishList = new WishList(user, product);

        wishListService.createWishList(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "Agregada  a Lista de deseos");
        return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }
}
