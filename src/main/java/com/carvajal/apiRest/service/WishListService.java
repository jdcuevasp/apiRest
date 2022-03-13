package com.carvajal.apiRest.service;

import com.carvajal.apiRest.dto.ProductDto;
import com.carvajal.apiRest.model.User;
import com.carvajal.apiRest.model.WishList;
import com.carvajal.apiRest.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductService productService;

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepository.findAllByUserOrder(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for(WishList wishList: wishLists ){
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return productDtos;

    }
}
