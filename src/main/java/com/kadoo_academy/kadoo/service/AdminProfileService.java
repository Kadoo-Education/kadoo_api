package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateAdminProfileDTO;
import com.kadoo_academy.kadoo.models.AdminProfile;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(CreateAdminProfileDTO adminProfile) {
        Optional<User> user = userRepository.findByEmail(adminProfile.email());

        if(user.isPresent()) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        User entity = new User();
        entity.setName(adminProfile.name());
        entity.setEmail(adminProfile.email());
        entity.setCpf(adminProfile.cpf());
        entity.setPassword(passwordEncoder.encode(adminProfile.password()));

        AdminProfile profile = new AdminProfile();

        profile.setUser(entity);

        entity.setAdmin(profile);

        userRepository.save(entity);

    }
}
