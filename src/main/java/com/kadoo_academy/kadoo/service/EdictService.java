package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateEdictDTO;
import com.kadoo_academy.kadoo.dto.response.EdictDTO;
import com.kadoo_academy.kadoo.dto.response.EdictDetailsDTO;
import com.kadoo_academy.kadoo.dto.response.ProfileUserResponseDTO;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictActiveDto;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictDto;
import com.kadoo_academy.kadoo.dto.response.StepDTO;
import com.kadoo_academy.kadoo.exceptions.customExceptions.EdictExistsException;
import com.kadoo_academy.kadoo.exceptions.customExceptions.EdictNotFoundException;
import com.kadoo_academy.kadoo.exceptions.customExceptions.ProfileNotAuthorizedException;
import com.kadoo_academy.kadoo.models.*;
import com.kadoo_academy.kadoo.models.enums.UserEnum;
import com.kadoo_academy.kadoo.repositories.EdictRepository;
import com.kadoo_academy.kadoo.repositories.UserEdictRepository;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import com.kadoo_academy.kadoo.security.service.TokenService;
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
    private UserRepository userRepository;

    @Autowired
    private UserEdictRepository userEdictRepository;

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
        entity.setStatus("Aberto");
        entity.setEndDate(createEdictDTO.endDate());
        entity.setStartDate(createEdictDTO.startDate());
        entity.setPdf(createEdictDTO.file());
        entity.setContact(createEdictDTO.contact());
        entity.setCategories(createEdictDTO.categories());
        entity.setLocation(createEdictDTO.location());
        entity.setUser(user);

        List<Step> steps = createEdictDTO.steps().stream()
                .map(stepDTO -> {
                    Step step = new Step();
                    step.setTitle(stepDTO.title());
                    step.setDescription(stepDTO.description());
                    step.setTime(stepDTO.time());
                    step.setDate(stepDTO.date());
                    step.setEdict(entity);

                    if ("Evento".equals(stepDTO.format())) {
                        Event event = new Event();

                        if("Presencial".equals(stepDTO.mode())) {
                            InPersonEvent inPersonEvent = new InPersonEvent();
                            inPersonEvent.setAddress(stepDTO.address());
                            inPersonEvent.setEvent(event);
                            event.setInPerson(inPersonEvent);
                        } else if("Online".equals(stepDTO.mode())) {
                            OnlineEvent onlineEvent = new OnlineEvent();
                            onlineEvent.setMeetingLink(stepDTO.meetingLink());
                            onlineEvent.setEvent(event);
                            event.setOnlineEvent(onlineEvent);
                        }
                        event.setStep(step);
                        step.setEvent(event);
                    }

                    else if ("Atividade".equals(stepDTO.format())) {
                        ActivityStep activityStep = new ActivityStep();
                        activityStep.setDueDate(stepDTO.dueDate());
                        activityStep.setFile(stepDTO.file());
                        step.setActivity(activityStep);
                        activityStep.setStep(step);
                    }
                    return step;
                })
                .toList();


        entity.setSteps(steps);
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
                                    dueDate = s.getActivity().getDueDate().toLocalDate();
                                }
                                activityFile = s.getActivity().getFile();
                            }

                            return new StepDTO(
                                    s.getId(),
                                    s.getTitle(),
                                    s.getDescription(),
                                    s.getTime(),
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
    /* public ResponseEdictDto getEdictById(Long id){
        Edict edict = edictRepository.findById(id).orElseThrow(() -> new EdictExistsException("Edict doesn't exist"));
        return new ResponseEdictDto(
                edict.getId(),edict.getTitle(),edict.getDescription(),
                edict.getLinkDoc(), edict.getStartDate(),
                edict.getEndDate(),edict.isActive(),edict.getTags());
    }*/

    public void updateEdictById(Long id, UpdateEdictDto updateEdictDto){
//        Edict edictEntity = edictRepository.findById(id).orElseThrow(() -> new EdictNotFoundException("Edict not found"));
//                edictEntity.setTitle(updateEdictDto.title());
//                edictEntity.setDescription(updateEdictDto.description());
//                edictEntity.setLinkDoc(updateEdictDto.linkDoc());
//                edictEntity.setStartDate(updateEdictDto.startDate());
//                edictEntity.setEndDate(updateEdictDto.endDate());
//                edictEntity.setActive(updateEdictDto.active());

//            edictRepository.save(edictEntity);
        }

    public void deleteEdictById(Long id){
        boolean idExists = edictRepository.existsById(id);
        if (!idExists){
            throw new EdictExistsException("Edict doesn't exist");
        }
        edictRepository.deleteById(id);
    }
    public void EdictActive(Long id, UpdateEdictActiveDto updateEdict){
     Edict edict = edictRepository.findById(id).orElseThrow(()-> new EdictExistsException("Edict not found"));
//     edict.setActive(updateEdict.getActive());
     edictRepository.save(edict);
    }
}

