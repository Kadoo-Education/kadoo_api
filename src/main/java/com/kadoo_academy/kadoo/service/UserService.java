package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.*;
import com.kadoo_academy.kadoo.exceptions.UserExistsException;
import com.kadoo_academy.kadoo.exceptions.UserNotFoundException;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Stream<ListUsersDTO> listUsers() {
        List<User> userEntity = userRepository.findAll();
        return userEntity.stream().map(user -> new ListUsersDTO(user.getId(), user.getName(),
                user.getEmail(), user.getType(), user.getActive(), user.getCreatedAt(), user.getUpdatedAt()));
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> userNotFound = userRepository.findById(id);

        if (userNotFound.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        return userNotFound;
    }

    public CreateUserDTO createUser(CreateUserDTO createUserDTO) {
        User userAlreadyExists = userRepository.findByEmail(createUserDTO.email());

        if (userAlreadyExists != null) {
            throw new UserExistsException();
        }

        User user = new User();
        user.setName(createUserDTO.name());
        user.setEmail(createUserDTO.email());
        user.setPassword(createUserDTO.password());

        userRepository.save(user);
        return createUserDTO;
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

        user.setId(id);
        user.setName(updateUserDTO.name());
        user.setEmail(updateUserDTO.email());
        user.setPassword(updateUserDTO.password());

        userRepository.save(user);
    }


}