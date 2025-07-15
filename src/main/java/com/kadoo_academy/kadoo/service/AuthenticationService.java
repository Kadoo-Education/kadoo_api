package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateUserDTO;
import com.kadoo_academy.kadoo.dto.request.LoginRequest;
import com.kadoo_academy.kadoo.dto.response.TokenResponseDTO;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import com.kadoo_academy.kadoo.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    public TokenResponseDTO login(LoginRequest login) {
        User user = userRepository.findByEmail(login.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String token = tokenService.generateToken(user);
        return new TokenResponseDTO(token);
    }

    public void register(CreateUserDTO createUserDTO) {
        Optional<User> userAlreadyExists = userRepository.findByEmail(createUserDTO.email());
        if (userAlreadyExists.isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        User user = new User();
        user.setName(createUserDTO.name());
        user.setEmail(createUserDTO.email());
        user.setPassword(createUserDTO.password());
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));

        userRepository.save(user);
    }
}
