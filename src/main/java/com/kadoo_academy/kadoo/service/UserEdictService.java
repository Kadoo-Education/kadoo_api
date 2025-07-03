package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.Request.MultipleUserSubscriptionDTO;
import com.kadoo_academy.kadoo.dto.Request.UserEdictDTO;
import com.kadoo_academy.kadoo.exceptions.customExceptions.UserEdictExistException;
import com.kadoo_academy.kadoo.models.Edict;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.models.UserEdict;
import com.kadoo_academy.kadoo.models.enums.UserEnum;
import com.kadoo_academy.kadoo.repositories.EdictRepository;
import com.kadoo_academy.kadoo.repositories.UserEdictRepository;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEdictService {
    @Autowired
    private UserEdictRepository userEdictRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EdictRepository edictRepository;

    public UserEdictDTO subscribeUserEdict(UserEdictDTO dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new IllegalArgumentException("User with ID " + dto.userId() + " was not found."));

        if (!UserEnum.STUDENT.equals(user.getType())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Only users of type 'STUDENT' are allowed to apply to edicts."
            );
        }

        Edict edict = edictRepository.findById(dto.edictId()).orElseThrow(() -> new IllegalArgumentException("Edict with ID " + dto.edictId() + " was not found."));

        boolean alreadySubscribed = userEdictRepository.existsByUserSubscribeAndEdict(user, edict);
        if (alreadySubscribed) {
            throw new UserEdictExistException();
        }

        UserEdict userEdict = new UserEdict();
        userEdict.setUserSubscribe(user);
        userEdict.setEdict(edict);
        userEdict.setNameSubscribe(user.getName());
        userEdictRepository.save(userEdict);
        return dto;
    }

    public List listEdictUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<UserEdict> userEdicts = userEdictRepository.findByUserSubscribe(user);
        return userEdicts.stream().map(UserEdict::getEdict).collect(Collectors.toList());
    }

    public MultipleUserSubscriptionDTO subscribeMultipleUsersToEdict(MultipleUserSubscriptionDTO dto) {
        Edict edict = edictRepository.findById(dto.edictId()).orElseThrow(() -> new IllegalArgumentException("Edict with ID " + dto.edictId() + " was not found"));

        List<Long> inputUserIds = dto.userIds();
        List<User> users = userRepository.findAllById(inputUserIds);

        List<Long> foundUserIds = users.stream().map(User::getId).toList();

        List<Long> notFoundUserIds = inputUserIds.stream().filter(id -> !foundUserIds.contains(id)).toList();

        if (!notFoundUserIds.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Users with the following IDs were not found: " + notFoundUserIds
            );
        }

        List<UserEdict> newSubscriptions = new ArrayList<>();
        List<Long> alreadySubscribedUserIds = new ArrayList<>();
        List<Long> notStudentUserIds = new ArrayList<>();

        for (User user : users) {
            if (!UserEnum.STUDENT.equals(user.getType())) {
                notStudentUserIds.add(user.getId());
                continue;
            }

            boolean alreadySubscribed = userEdictRepository.existsByUserSubscribeAndEdict(user, edict);
            if (alreadySubscribed) {
                alreadySubscribedUserIds.add(user.getId());
                continue;
            }

            UserEdict userEdict = new UserEdict();
            userEdict.setUserSubscribe(user);
            userEdict.setEdict(edict);
            userEdict.setNameSubscribe(user.getName());
            newSubscriptions.add(userEdict);
        }

        userEdictRepository.saveAll(newSubscriptions);

        if (!alreadySubscribedUserIds.isEmpty() || !notStudentUserIds.isEmpty()) {
            StringBuilder message = new StringBuilder("Some applications were ignored:\n");
            if (!alreadySubscribedUserIds.isEmpty()) {
                message.append(" - Users already signed up for the edict: ").append(alreadySubscribedUserIds).append("\n");
            }
            if (!notStudentUserIds.isEmpty()) {
                message.append(" - Users that aren't students: ").append(notStudentUserIds).append("\n");
            }
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    message.toString()
            );
        }
        return dto;
    }
}