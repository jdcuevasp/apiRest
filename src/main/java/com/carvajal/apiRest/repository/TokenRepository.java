package com.carvajal.apiRest.repository;

import com.carvajal.apiRest.model.Token;
import com.carvajal.apiRest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token , Long> {

    Token findByUser(User user);
    Token findByToken(String token);
}
