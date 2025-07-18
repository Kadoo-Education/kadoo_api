package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateEnterpriseProfileDTO;
import com.kadoo_academy.kadoo.dto.request.CreateMentorProfileDTO;
import com.kadoo_academy.kadoo.models.EnterpriseProfile;
import com.kadoo_academy.kadoo.models.MentorProfile;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnterpriseProfileService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(CreateEnterpriseProfileDTO enterpriseProfile) {
        Optional<User> user = userRepository.findByEmail(enterpriseProfile.email());

        if(user.isPresent()) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        User entity = new User();
        entity.setName(enterpriseProfile.name());
        entity.setEmail(enterpriseProfile.email());
        entity.setCpf(enterpriseProfile.cpf());
        entity.setPassword(passwordEncoder.encode(enterpriseProfile.password()));

        EnterpriseProfile profile = new EnterpriseProfile();
        profile.setCnpj(enterpriseProfile.cnpj());

        profile.setUser(entity);

        entity.setEnterprise(profile);

        userRepository.save(entity);
    }
}
