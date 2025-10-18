package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.response.ActivityDTO;
import com.kadoo_academy.kadoo.dto.response.EventDTO;
import com.kadoo_academy.kadoo.dto.response.GetAllStepDTO;
import com.kadoo_academy.kadoo.dto.response.StepDetailsDTO;
import com.kadoo_academy.kadoo.models.*;
import com.kadoo_academy.kadoo.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StepService {

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private EdictRepository edictRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InPersonEventRepository inPersonEventRepository;

    @Autowired
    private OnlineEventRepository onlineEventRepository;

    @Autowired
    private ActivityStepRepository activityStepRepository;

    public List<GetAllStepDTO> getAll() {
        List<Step> steps = this.stepRepository.findAll();

        return steps.stream().map(step -> {
            Event event = step.getEvent();

            EventDTO eventDTO = null;
            ActivityDTO activityDTO = null;
            String kind;

            if (event != null) {
                kind = "event";

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
            } else {
                kind = "activity";

                // ajuste aqui conforme seu modelo:
                // se os campos estão em Step:
                activityDTO = new ActivityDTO(
                        step.getActivity().getDueDate(),      // ou step.getActivity().getDueDate()
                        step.getActivity().getFile()  // ou step.getActivity().getFile()
                );
            }

            return new GetAllStepDTO(
                    step.getId(),
                    step.getTitle(),
                    step.getDescription(),
                    step.getDate(),
                    step.getStatus(),
                    kind,
                    eventDTO,
                    activityDTO
            );
        }).toList();
    }


    public List<GetAllStepDTO> getByEdictId(Long id) {
        edictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Edital não encontrado com id: " + id));

        List<Step> steps = this.stepRepository.findByEdictId(id);

        return steps.stream().map(step -> {
            EventDTO eventDTO = null;
            ActivityDTO activityDTO = null;
            String kind = "none";

            Event event = step.getEvent();
            if (event != null) {
                kind = "event";

                OnlineEvent online = event.getOnlineEvent();
                InPersonEvent inPerson = event.getInPerson();

                if (online != null) {
                    eventDTO = new EventDTO(
                            event.getId(),
                            "online",
                            online.getMode(),
                            online.getFormat(),
                            online.getMeetingLink(),
                            null
                    );
                } else if (inPerson != null) {
                    eventDTO = new EventDTO(
                            event.getId(),
                            "presencial",
                            inPerson.getMode(),
                            inPerson.getFormat(),
                            null,
                            inPerson.getAddress()
                    );
                }
            } else {
                ActivityStep activity = step.getActivity();
                if (activity != null) {
                    kind = "activity";
                    activityDTO = new ActivityDTO(
                            activity.getDueDate(),
                            activity.getFile()
                    );
                }
            }

            return new GetAllStepDTO(
                    step.getId(),
                    step.getTitle(),
                    step.getDescription(),
                    step.getDate(),
                    step.getStatus(),
                    kind,
                    eventDTO,
                    activityDTO
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

    @Transactional
    public void deleteStep(Long stepId) {
        Step step = stepRepository.findById(stepId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Etapa não encontrada."));

        if (step.getEvent() != null) {
            eventRepository.delete(step.getEvent());
            step.setEvent(null);
        }

        if (step.getActivity() != null) {
            activityStepRepository.delete(step.getActivity());
            step.setActivity(null);
        }

        stepRepository.delete(step);
    }
}
