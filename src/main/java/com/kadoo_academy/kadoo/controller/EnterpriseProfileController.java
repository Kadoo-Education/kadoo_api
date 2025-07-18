package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateEnterpriseProfileDTO;
import com.kadoo_academy.kadoo.dto.request.CreateMentorProfileDTO;
import com.kadoo_academy.kadoo.service.EnterpriseProfileService;
import com.kadoo_academy.kadoo.service.MentorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprise-profile")
public class EnterpriseProfileController {

    @Autowired
    private EnterpriseProfileService enterpriseProfileService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateEnterpriseProfileDTO enterprise) {
        try {
            enterpriseProfileService.create(enterprise);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
