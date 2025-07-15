package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.MultipleUserSubscriptionDTO;
import com.kadoo_academy.kadoo.dto.request.UserEdictDTO;
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

    @GetMapping("/{id}")
    public List listEdictUser (@PathVariable Long id) {
        return userEdictService.listEdictUser(id);
    }

    @PostMapping("/subscribe-multiple")
    public ResponseEntity<String> subscribeMultipleUsersToEdict(@RequestBody MultipleUserSubscriptionDTO dto) {
        userEdictService.subscribeMultipleUsersToEdict(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
