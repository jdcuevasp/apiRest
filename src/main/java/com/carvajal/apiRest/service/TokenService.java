package com.carvajal.apiRest.service;

import com.carvajal.apiRest.model.Token;
import com.carvajal.apiRest.model.User;
import com.carvajal.apiRest.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    public void saveCorfirmationToken(Token authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public Token getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token){
        final Token authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(token)){
            return  null;
        }
        return authenticationToken.getUser();

    }

    public void authenticate(String token) throws Exception {
        if(Objects.isNull(token)){
            throw new Exception("Token no presente");
        }
        if(Objects.isNull(getUser(token))){
            throw new Exception("Token no valido");
        }
    }
}
