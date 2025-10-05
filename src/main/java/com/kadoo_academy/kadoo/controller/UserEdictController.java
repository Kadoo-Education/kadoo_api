package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.MultipleUserSubscriptionDTO;
import com.kadoo_academy.kadoo.dto.request.UserEdictDTO;
import com.kadoo_academy.kadoo.dto.response.EdictAttachUserDTO;
import com.kadoo_academy.kadoo.security.service.TokenService;
import com.kadoo_academy.kadoo.service.UserEdictService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-edict")
public class UserEdictController {

    @Autowired
    private UserEdictService userEdictService;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<UserEdictDTO> subscribeUserEdict (
            @RequestBody UserEdictDTO dto
    ) {
        userEdictService.subscribeUserEdict(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<EdictAttachUserDTO>> listEdictUser (
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "");
        List<EdictAttachUserDTO> edicts = userEdictService.listEdictUser(token);

        return ResponseEntity.status(HttpStatus.OK).body(edicts);
    }

    @PostMapping("/subscribe-multiple")
    public ResponseEntity<String> subscribeMultipleUsersToEdict(@RequestBody MultipleUserSubscriptionDTO dto) {
        userEdictService.subscribeMultipleUsersToEdict(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
