package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.LoginRequest;
import com.kadoo_academy.kadoo.dto.response.TokenResponseDTO;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import com.kadoo_academy.kadoo.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public TokenResponseDTO login(LoginRequest login) {
        User user = userRepository.findByEmail(login.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String token = tokenService.generateToken(user);
        return new TokenResponseDTO(token);
    }
}
