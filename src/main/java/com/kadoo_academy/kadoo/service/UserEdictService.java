package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.models.UserEdictDTO;
import com.kadoo_academy.kadoo.models.enums.UserEnum;
import com.kadoo_academy.kadoo.repositories.UserEdictRepository;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String subscribeUserEdict(UserEdictDTO dto) {
        User user = userRepository.findById(dto.getUserSubscribe()).orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + dto.getUserSubscribe() + " não encontrado."));

        if (!UserEnum.STUDENT.equals(user.getType())) {
            throw new IllegalStateException("Apenas usuários do tipo STUDENT podem se inscrever em editais.");
        }

        Edict edict = edictRepository.findById(dto.getEdict()).orElseThrow(() -> new IllegalArgumentException("Edital com ID " + dto.getEdict() + " não encontrado."));

        boolean alreadySubscribed = userEdictRepository.existsByUserSubscribeAndEdict(user, edict);
        if (alreadySubscribed) {
            throw new IllegalStateException("Usuário já está inscrito neste edital.");
        }

        UserEdict userEdict = new UserEdict();
        userEdict.setUserSubscribe(user);
        userEdict.setEdict(edict);
        userEdictRepository.save(userEdict);

        return "Inscrição realizada com sucesso.";
    }

    public List<Edict> listEdictsUser(UserEdictDTO dto) {
        User user = userRepository.findById(dto.getUserSubscribe()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        List<UserEdict> userEdicts = userEdictRepository.findByUserSubscribe(user);

            return userEdicts.stream().map(UserEdict::getEdict).collect(Collectors.toList());
    }

    public String subscribeMultipleUsersToEdict(Long edictId, List<Long> userIds) {
        Edict edict = edictRepository.findById(edictId).orElseThrow(() -> new IllegalArgumentException("Edital com ID " + edictId + " não encontrado."));

        List<User> users = userRepository.findAllById(userIds);

        List<UserEdict> newSubscriptions = new ArrayList<>();

        for (User user : users) {
            if (!UserEnum.STUDENT.equals(user.getType())) {
                continue;
            }

            boolean alreadySubscribed = userEdictRepository.existsByUserSubscribeAndEdict(user, edict);
            if (alreadySubscribed) {
                continue;
            }

            UserEdict userEdict = new UserEdict();
            userEdict.setUserSubscribe(user);
            userEdict.setEdict(edict);
            newSubscriptions.add(userEdict);
        }

        userEdictRepository.saveAll(newSubscriptions);

        return "Alunos inscritos com sucesso.";
    }
}
