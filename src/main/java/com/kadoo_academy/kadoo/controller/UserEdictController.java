package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.models.MultipleUserSubscriptionDTO;
import com.kadoo_academy.kadoo.models.UserEdictDTO;
import com.kadoo_academy.kadoo.service.UserEdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-edict")
public class UserEdictController {

    @Autowired
    private UserEdictService userEdictService;

    @PostMapping()
    public ResponseEntity<String> subscribeUserEdict (@RequestBody UserEdictDTO dto) {
        userEdictService.subscribeUserEdict(dto);
        return ResponseEntity.ok("Você se inscreveu no edital!");
    }

    @GetMapping()
    public List listEdictsUser (@RequestBody UserEdictDTO dto) {
        return userEdictService.listEdictsUser(dto);
    }

    @PostMapping("/subscribe-multiple")
    public ResponseEntity<String> subscribeMultipleUsersToEdict(@RequestBody MultipleUserSubscriptionDTO dto) {
        try {
            String result = userEdictService.subscribeMultipleUsersToEdict(dto.getEdictId(), dto.getUserIds());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao inscrever alunos: " + e.getMessage());
        }
    }
}
