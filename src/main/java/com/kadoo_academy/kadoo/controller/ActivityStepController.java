package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateActivityStepDTO;
import com.kadoo_academy.kadoo.service.ActivityStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity-step")
public class ActivityStepController {

    @Autowired
    private ActivityStepService activityStepService;

    @PostMapping
    public ResponseEntity<?> createActivityStep(@RequestBody CreateActivityStepDTO createActivityStepDTO) {
        activityStepService.createActivityStep(createActivityStepDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityStepService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
