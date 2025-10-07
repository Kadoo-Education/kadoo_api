package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.response.ActivityDTO;
import com.kadoo_academy.kadoo.dto.response.EventDTO;
import com.kadoo_academy.kadoo.dto.response.GetAllStepDTO;
import com.kadoo_academy.kadoo.dto.response.StepDetailsDTO;
import com.kadoo_academy.kadoo.models.Event;
import com.kadoo_academy.kadoo.models.InPersonEvent;
import com.kadoo_academy.kadoo.models.OnlineEvent;
import com.kadoo_academy.kadoo.models.Step;
import com.kadoo_academy.kadoo.repositories.EdictRepository;
import com.kadoo_academy.kadoo.repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepService {

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private EdictRepository edictRepository;

    public List<GetAllStepDTO> getAll() {
        List<Step> steps = this.stepRepository.findAll();

        return steps.stream().map(step -> {
            Event event = step.getEvent();
            EventDTO eventDTO = null;
            if(event != null) {
                if (event.getOnlineEvent() != null) {
                    OnlineEvent online = event.getOnlineEvent();
                    eventDTO = new EventDTO(
                            event.getId(),
                            "online",
                            online.getMode(),
                            online.getFormat(),
                            online.getMeetingLink(),
                            null
                    );
                } else if (event.getInPerson() != null) {
                    InPersonEvent inPerson = event.getInPerson();
                    eventDTO = new EventDTO(
                            event.getId(),
                            "presencial",
                            inPerson.getMode(),
                            inPerson.getFormat(),
                            null,
                            inPerson.getAddress()
                    );
                }
            }

            return new GetAllStepDTO(
                    step.getId(),
                    step.getTitle(),
                    step.getDescription(),
                    step.getDate(),
                    step.getStatus(),
                    eventDTO
            );
        }).toList();
    }

    public List<GetAllStepDTO> getByEdictId(Long id){
        edictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Edital não encontrado com id: " + id));

        List<Step> steps = this.stepRepository.findByEdictId(id);

        return steps.stream().map(step -> {
            EventDTO eventDTO = null;

            if (step.getEvent() != null) {
                if (step.getEvent().getOnlineEvent() != null) {
                    var online = step.getEvent().getOnlineEvent();
                    eventDTO = new EventDTO(
                            step.getEvent().getId(),
                            "online",
                            online.getMode(),
                            online.getFormat(),
                            online.getMeetingLink(),
                            null
                    );
                } else if (step.getEvent().getInPerson() != null) {
                    var inPerson = step.getEvent().getInPerson();
                    eventDTO = new EventDTO(
                            step.getEvent().getId(),
                            "presencial",
                            inPerson.getMode(),
                            inPerson.getFormat(),
                            null,
                            inPerson.getAddress()
                    );
                }
            }

            return new GetAllStepDTO(
                    step.getId(),
                    step.getTitle(),
                    step.getDescription(),
                    step.getDate(),
                    step.getStatus(),
                    eventDTO
            );
        }).toList();
    }

    public StepDetailsDTO getById(Long id) {
        Step step = stepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etapa não encontrada com id: " + id));

        final String type;
        if (step.getEvent() != null) {
            type = "Evento";
        } else if (step.getActivity() != null) {
            type = "Atividade";
        } else {
            throw new RuntimeException("Etapa sem Evento ou Atividade. ID: " + id);
        }

        EventDTO eventDTO = null;
        ActivityDTO activityDTO = null;

        if ("Evento".equals(type)) {
            Event event = step.getEvent();
            if (event != null) {
                if (event.getOnlineEvent() != null) {
                    OnlineEvent online = event.getOnlineEvent();
                    eventDTO = new EventDTO(
                            event.getId(),
                            "online",
                            online.getMode(),
                            online.getFormat(),
                            online.getMeetingLink(),
                            null
                    );
                } else if (event.getInPerson() != null) {
                    InPersonEvent inPerson = event.getInPerson();
                    eventDTO = new EventDTO(
                            event.getId(),
                            "presencial",
                            inPerson.getMode(),
                            inPerson.getFormat(),
                            null,
                            inPerson.getAddress()
                    );
                }
            }
        } else {
            if (step.getActivity() != null) {
                activityDTO = new ActivityDTO(
                        step.getActivity().getDueDate(),
                        step.getActivity().getFile()
                );
            }
        }

        return new StepDetailsDTO(
                step.getId(),
                step.getTitle(),
                step.getDescription(),
                step.getDate(),
                step.getStatus(),
                type,
                eventDTO,
                activityDTO
        );
    }
}
