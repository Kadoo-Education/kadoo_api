package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateMentorProfileDTO;
import com.kadoo_academy.kadoo.dto.response.ListMentorProfileDTO;
import com.kadoo_academy.kadoo.models.MentorProfile;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MentorProfileService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public void create(CreateMentorProfileDTO mentorProfile) {
        Optional<User> user = userRepository.findByEmail(mentorProfile.email());

        if(user.isPresent()) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        User entity = new User();
        entity.setName(mentorProfile.name());
        entity.setEmail(mentorProfile.email());
        entity.setCpf(mentorProfile.cpf());
        entity.setPassword(passwordEncoder.encode(mentorProfile.password()));

        MentorProfile profile = new MentorProfile();
        profile.setDescription(mentorProfile.description());
        profile.setArea(mentorProfile.area());


        profile.setUser(entity);

        entity.setMentor(profile);

        userRepository.save(entity);
    }

    public List<ListMentorProfileDTO> getAll() {
        return userRepository.findAll().stream()
                .filter(user -> user.getMentor() != null)
                .map(user -> {
                    MentorProfile mentor = user.getMentor();
                    return new ListMentorProfileDTO(
                            user.getId(),
                            user.getName(),
                            user.getMentor().getArea().get(0)
                    );
                })
                .toList();
    }
}
