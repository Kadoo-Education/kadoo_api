package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateAdminProfileDTO;
import com.kadoo_academy.kadoo.service.AdminProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin-profile")
public class AdminProfileController {

    @Autowired
    private AdminProfileService adminProfileService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateAdminProfileDTO profile) {
        adminProfileService.create(profile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
