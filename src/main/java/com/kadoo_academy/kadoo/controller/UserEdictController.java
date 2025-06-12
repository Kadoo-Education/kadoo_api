package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.MultipleUserSubscriptionDTO;
import com.kadoo_academy.kadoo.dto.UserEdictDTO;
import com.kadoo_academy.kadoo.service.UserEdictService;
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

    @PostMapping()
    public ResponseEntity<UserEdictDTO> subscribeUserEdict (@RequestBody UserEdictDTO dto) {
        userEdictService.subscribeUserEdict(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public List listEdictUser (@RequestBody UserEdictDTO dto) {
        return userEdictService.listEdictUser(dto);
    }

    @PostMapping("/subscribe-multiple")
    public ResponseEntity<String> subscribeMultipleUsersToEdict(@RequestBody MultipleUserSubscriptionDTO dto) {
        userEdictService.subscribeMultipleUsersToEdict(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
