package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateStudentProfileDTO;
import com.kadoo_academy.kadoo.models.StudentProfile;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void create(CreateStudentProfileDTO studentProfile) {
        Optional<User> user = userRepository.findByEmail(studentProfile.email());

        if(user.isPresent()) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        User entity = new User();
        entity.setName(studentProfile.name());
        entity.setEmail(studentProfile.email());
        entity.setCpf(studentProfile.cpf());
        entity.setPassword(passwordEncoder.encode(studentProfile.password()));

        StudentProfile profile = new StudentProfile();
        profile.setBirthDate(studentProfile.birthDate());
        profile.setUser(entity);

        entity.setStudent(profile);

        userRepository.save(entity);
    }
}
