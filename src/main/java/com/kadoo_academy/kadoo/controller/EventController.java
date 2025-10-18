package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateInPersonEventDTO;
import com.kadoo_academy.kadoo.dto.request.CreateOnlineEventDTO;
import com.kadoo_academy.kadoo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/in-person")
    public ResponseEntity<?> createInPersonEvent(@RequestBody CreateInPersonEventDTO createInPersonEventDTO) {
        eventService.createInPersonEvent(createInPersonEventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/online")
    public ResponseEntity<?> createOnlineEvent(@RequestBody CreateOnlineEventDTO createOnlineEventDTO) {
        eventService.createOnlineEvent(createOnlineEventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
