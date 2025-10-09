package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateActivityResponseDTO;
import com.kadoo_academy.kadoo.dto.response.ProfileUserResponseDTO;
import com.kadoo_academy.kadoo.security.service.TokenService;
import com.kadoo_academy.kadoo.service.ActivityResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity-response")
public class ActivityResponseController {

    @Autowired
    private ActivityResponseService activityResponseService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/{stepId}")
    public ResponseEntity<?> userAlreadyResponseActivity(
            @PathVariable Long stepId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "");
        ProfileUserResponseDTO profile = tokenService.decodeToken(token);

        Boolean userAlreadyResponse = activityResponseService.hasUserSubmittedForStep(stepId, profile.id());

        return ResponseEntity.status(HttpStatus.OK).body(userAlreadyResponse);
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestBody CreateActivityResponseDTO  createActivityResponseDTO,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "");
        ProfileUserResponseDTO profile = tokenService.decodeToken(token);

        activityResponseService.create(createActivityResponseDTO, profile.id());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
