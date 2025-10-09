package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateActivityResponseDTO;
import com.kadoo_academy.kadoo.models.ActivityResponse;
import com.kadoo_academy.kadoo.models.Step;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.repositories.ActivityResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ActivityResponseService {

    @Autowired
    private ActivityResponseRepository activityResponseRepository;

    public boolean hasUserSubmittedForStep(Long stepId, Long userId) {
        return activityResponseRepository.existsByUser_IdAndStep_Id(userId, stepId);
    }

    public void create(CreateActivityResponseDTO activityResponseDTO, Long userId) {

        User user = new User();
        user.setId(userId);

        Step step = new Step();
        step.setId(activityResponseDTO.stepId());

        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setResponse(activityResponseDTO.response());
        activityResponse.setPdf(activityResponseDTO.pdf());
        activityResponse.setStep(step);
        activityResponse.setUser(user);
        activityResponse.setDueDate(LocalDate.now());

        activityResponseRepository.save(activityResponse);
    }
}
