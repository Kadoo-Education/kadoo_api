package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.response.GetAllStepDTO;
import com.kadoo_academy.kadoo.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/steps")
public class StepController {

    @Autowired
    private StepService stepService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<GetAllStepDTO> steps = stepService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(steps);
    }

    @GetMapping("/edict/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        List<GetAllStepDTO> steps = stepService.getByEdictId(id);

        return ResponseEntity.status(HttpStatus.OK).body(steps);
    }
}
