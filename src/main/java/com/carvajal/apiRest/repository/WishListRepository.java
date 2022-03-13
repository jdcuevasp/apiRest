package com.carvajal.apiRest.repository;

import com.carvajal.apiRest.model.User;
import com.carvajal.apiRest.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findAllByUserOrder(User user);
}
