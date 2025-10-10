package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateInPersonEventDTO;
import com.kadoo_academy.kadoo.dto.request.CreateOnlineEventDTO;
import com.kadoo_academy.kadoo.models.*;
import com.kadoo_academy.kadoo.repositories.EdictRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    @Autowired
    private EdictRepository edictRepository;

    @Transactional
    public void createInPersonEvent(CreateInPersonEventDTO dto) {
        Edict edict = edictRepository.findById(dto.edictId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Edital não encontrado."));

        Step step = new Step();
        step.setTitle(dto.title());
        step.setDescription(dto.description());
        step.setDate(dto.date());
        step.setEdict(edict);

        Event event = new Event();
        event.setStep(step);
        step.setEvent(event);
        step.setActivity(null);

        InPersonEvent inPerson = new InPersonEvent();
        inPerson.setAddress(dto.address());
        inPerson.setMode(dto.mode());
        inPerson.setFormat(dto.format());
        inPerson.setEvent(event);
        event.setInPerson(inPerson);
        event.setOnlineEvent(null);

        edict.getSteps().add(step);
        edictRepository.save(edict);
    }

    @Transactional
    public void createOnlineEvent(CreateOnlineEventDTO dto) {
        Edict edict = edictRepository.findById(dto.edictId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Edital não encontrado."));

        Step step = new Step();
        step.setTitle(dto.title());
        step.setDescription(dto.description());
        step.setDate(dto.date());
        step.setEdict(edict);

        Event event = new Event();
        event.setStep(step);
        step.setEvent(event);
        step.setActivity(null);

        OnlineEvent onlineEvent = new OnlineEvent();
        onlineEvent.setMeetingLink(dto.meetingLink());
        onlineEvent.setMode(dto.mode());
        onlineEvent.setFormat(dto.format());
        onlineEvent.setEvent(event);

        event.setOnlineEvent(onlineEvent);
        event.setInPerson(null);

        edict.getSteps().add(step);
        edictRepository.save(edict);
    }
}
