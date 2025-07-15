package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateStudentProfileDTO;
import com.kadoo_academy.kadoo.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-profile")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateStudentProfileDTO createStudentProfileDTO) {
        try {
            studentProfileService.create(createStudentProfileDTO);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
