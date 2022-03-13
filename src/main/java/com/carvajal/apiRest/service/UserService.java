package com.carvajal.apiRest.service;

import com.carvajal.apiRest.dto.ResponseDto;
import com.carvajal.apiRest.dto.SignInDto;
import com.carvajal.apiRest.dto.SignInResponseDto;
import com.carvajal.apiRest.dto.SignUpDto;
import com.carvajal.apiRest.model.Token;
import com.carvajal.apiRest.model.User;
import com.carvajal.apiRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Transactional
    public ResponseDto signUp(SignUpDto signUpDto) {

        //userRepository.findByEmail(signUpDto.getEmail());
        String encryptedassword = signUpDto.getPassword();
        try{
            encryptedassword = hashPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user =new User(signUpDto.getFirstName(), signUpDto.getLastName(),
                signUpDto.getEmail(), encryptedassword);

        userRepository.save(user);

        final Token authenticationToken = new Token(user);
        tokenService.saveCorfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("ok", "Usuario creado exitosamente!");
        return  responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(signInDto.getEmail());

        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new RuntimeException("Contraseña Incorrecta");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Token token = tokenService.getToken(user);
        if(Objects.isNull(token) ){
            throw new RuntimeException("Token no presente");

        }
        return  new SignInResponseDto("ok", token.getToken());

    }
}
