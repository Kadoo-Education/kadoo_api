package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateEdictDTO;
import com.kadoo_academy.kadoo.dto.response.EdictDTO;
import com.kadoo_academy.kadoo.dto.response.ProfileUserResponseDTO;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictActiveDto;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictDto;
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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        entity.setStatus("Ativo");
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

                    if (stepDTO.type() == "Evento") {
                        Event event = new Event();
                        event.setStep(step);

                        if(stepDTO.format() == "Presencial") {
                            InPersonEvent inPersonEvent = new InPersonEvent();
                            inPersonEvent.setAddress(stepDTO.adress());
                            inPersonEvent.setEvent(event);
                        }

                        if(stepDTO.format() == "Online") {
                            OnlineEvent onlineEvent = new OnlineEvent();
                            onlineEvent.setMeetingLink(stepDTO.meetinglink());
                            onlineEvent.setEvent(event);
                        }
                    }

                    else if (stepDTO.type() == "Atividade") {
                        ActivityStep activityStep = new ActivityStep();
                        activityStep.setTitle(stepDTO.activityTitle());
                        activityStep.setDueDate(stepDTO.dueDate());
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
                        e.getPdf(),
                        e.getStatus(),
                        e.getCategories()
                ))
                .toList();
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

