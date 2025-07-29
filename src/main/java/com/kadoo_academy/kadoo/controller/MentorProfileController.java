package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateMentorProfileDTO;
import com.kadoo_academy.kadoo.dto.response.ListMentorProfileDTO;
import com.kadoo_academy.kadoo.service.MentorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentor-profile")
public class MentorProfileController {

    @Autowired
    private MentorProfileService mentorProfileService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateMentorProfileDTO mentor) {
        try {
            mentorProfileService.create(mentor);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<ListMentorProfileDTO> mentors = mentorProfileService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(mentors);
    }
}
