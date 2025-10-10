package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateActivityStepDTO;
import com.kadoo_academy.kadoo.models.ActivityStep;
import com.kadoo_academy.kadoo.models.Edict;
import com.kadoo_academy.kadoo.models.Step;
import com.kadoo_academy.kadoo.repositories.EdictRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ActivityStepService {

    @Autowired
    private EdictRepository edictRepository;

    @Transactional
    public void createActivityStep(CreateActivityStepDTO dto) {
        Edict edict = edictRepository.findById(dto.edictId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Edital não encontrado."));

//        if (dto.dueDate() != null && dto.date() != null && dto.dueDate().isBefore(dto.date())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "dueDate não pode ser antes de date.");
//        }

        Step step = new Step();
        step.setTitle(dto.title());
        step.setDescription(dto.description());
        step.setDate(dto.date());
        step.setEdict(edict);

        ActivityStep activity = new ActivityStep();
        activity.setDueDate(dto.dueDate());
        activity.setFile(dto.file());
        activity.setStep(step);

        step.setActivity(activity);
        step.setEvent(null);

        edict.getSteps().add(step);
        edictRepository.save(edict);
    }
}
