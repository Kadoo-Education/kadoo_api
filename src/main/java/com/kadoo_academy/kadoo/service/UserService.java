package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateUserDTO;
import com.kadoo_academy.kadoo.dto.request.UpdateUserDTO;
import com.kadoo_academy.kadoo.dto.response.ListUsersDTO;
import com.kadoo_academy.kadoo.exceptions.customExceptions.UserExistsException;
import com.kadoo_academy.kadoo.exceptions.customExceptions.UserNotFoundException;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Stream<ListUsersDTO> listUsers() {
        List<User> userEntity = userRepository.findAll();
        return userEntity.stream().map(user -> new ListUsersDTO(user.getId(), user.getName(),
                user.getEmail(), user.getActive(), user.getCreatedAt()));
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> userNotFound = userRepository.findById(id);

        if (userNotFound.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        return userNotFound;
    }

    public CreateUserDTO createUser(CreateUserDTO responseUserDTO) {
        Optional<User> userAlreadyExists = userRepository.findByEmail(responseUserDTO.email());

        if (userAlreadyExists != null) {
            throw new UserExistsException();
        }

        String encoder = this.passwordEncoder.encode(responseUserDTO.password());


        User user = new User();
        user.setName(responseUserDTO.name());
        user.setEmail(responseUserDTO.email());
        user.setPassword(encoder);

        userRepository.save(user);
        return responseUserDTO;
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User entity = new User();
        entity.setId(user.get().getId());
        userRepository.delete(entity);

    }

    public void updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        String encoder = this.passwordEncoder.encode(updateUserDTO.password());


        user.setId(id);
        user.setName(updateUserDTO.name());
        user.setEmail(updateUserDTO.email());
        user.setPassword(encoder);

        userRepository.save(user);
    }


}