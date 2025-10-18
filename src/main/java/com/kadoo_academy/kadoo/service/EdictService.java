package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateEdictDTO;
import com.kadoo_academy.kadoo.dto.response.EdictDTO;
import com.kadoo_academy.kadoo.dto.response.EdictDetailsDTO;
import com.kadoo_academy.kadoo.dto.response.ProfileUserResponseDTO;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictActiveDto;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictDTO;
import com.kadoo_academy.kadoo.dto.response.StepDTO;
import com.kadoo_academy.kadoo.exceptions.customExceptions.EdictExistsException;
import com.kadoo_academy.kadoo.exceptions.customExceptions.EdictNotFoundException;
import com.kadoo_academy.kadoo.models.*;
import com.kadoo_academy.kadoo.models.enums.EdictStatusEnum;
import com.kadoo_academy.kadoo.repositories.EdictRepository;
import com.kadoo_academy.kadoo.repositories.UserEdictRepository;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import com.kadoo_academy.kadoo.security.service.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


@Service
public class EdictService {
    @Autowired
    private EdictRepository edictRepository;

    @Autowired
    private TokenService tokenService;

    public void create(CreateEdictDTO createEdictDTO, String token){
        ProfileUserResponseDTO profile = tokenService.decodeToken(token);

        User user = new User();
        user.setId(profile.id());

        Edict entity = new Edict();
        entity.setTitle(createEdictDTO.title());
        entity.setDescription(createEdictDTO.description());
        entity.setOrganizer(createEdictDTO.organizer());
        entity.setStatus(EdictStatusEnum.ABERTO);
        entity.setEndDate(createEdictDTO.endDate());
        entity.setStartDate(createEdictDTO.startDate());
        entity.setPdf(createEdictDTO.file());
        entity.setContact(createEdictDTO.contact());
        entity.setCategories(createEdictDTO.categories());
        entity.setLocation(createEdictDTO.location());
        entity.setUser(user);
        edictRepository.save(entity);
    }

    public List<EdictDTO> getAll(){

        List<Edict> edicts = edictRepository.findAll();

        return edicts.stream()
                .map(e -> new EdictDTO(
                        e.getId(),
                        e.getTitle(),
                        e.getDescription(),
                        e.getStartDate(),
                        e.getEndDate(),
                        e.getOrganizer(),
                        e.getContact(),
                        e.getPdf(),
                        e.getStatus(),
                        e.getLocation(),
                        e.getCategories()
                ))
                .toList();
    }


    public EdictDetailsDTO getById(Long id){
        Edict edict = edictRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Edital não encontrado."));


        return new EdictDetailsDTO(
                edict.getId(),
                edict.getTitle(),
                edict.getDescription(),
                edict.getStartDate(),
                edict.getEndDate(),
                edict.getOrganizer(),
                edict.getContact(),
                edict.getPdf(),
                edict.getStatus(),
                edict.getLocation(),
                edict.getCategories(),
                edict.getSteps().stream()
                        .map(s -> {

                            String format = null;
                            if (s.getEvent() != null) {
                                format = "Evento";
                            } else if (s.getActivity() != null) {
                                format = "Atividade";
                            }


                            String mode = null;
                            String address = null;
                            String meetingLink = null;
                            if (s.getEvent() != null) {
                                if (s.getEvent().getInPerson() != null) {
                                    mode = "Presencial";
                                    address = s.getEvent().getInPerson().getAddress();
                                } else if (s.getEvent().getOnlineEvent() != null) {
                                    mode = "Online";
                                    meetingLink = s.getEvent().getOnlineEvent().getMeetingLink();
                                }
                            }

                            LocalDate dueDate = null;
                            String activityFile = null;
                            if (s.getActivity() != null) {
                                if (s.getActivity().getDueDate() != null) {
                                    dueDate = s.getActivity().getDueDate();
                                }
                                activityFile = s.getActivity().getFile();
                            }

                            return new StepDTO(
                                    s.getId(),
                                    s.getTitle(),
                                    s.getDescription(),
                                    s.getDate(),
                                    format,
                                    mode,
                                    meetingLink,
                                    address,
                                    dueDate,
                                    activityFile
                            );
                        })
                        .toList()
        );
    }

    @Transactional
    public void update(Long id, UpdateEdictDTO dto) {

        Edict edict = edictRepository.findById(id).orElseThrow(() -> new EdictNotFoundException("Edict not found"));

        edict.setTitle(dto.title());
        edict.setDescription(dto.description());
        edict.setOrganizer(dto.organizer());
        edict.setContact(dto.contact());
        edict.setLocation(dto.location());
        edict.setStartDate(dto.startDate());
        edict.setEndDate(dto.endDate());
        edict.setPdf(dto.file());
        edict.getCategories().clear();
        edict.getCategories().addAll(dto.categories());

        edictRepository.save(edict);
    }

    public void delete(Long id){
        boolean edictAlreadyExists = edictRepository.existsById(id);
        if (!edictAlreadyExists){
            throw new EdictExistsException("Edital não encontrado.");
        }
        edictRepository.deleteById(id);
    }
}

