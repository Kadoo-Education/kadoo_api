package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateActivityStepDTO;
import com.kadoo_academy.kadoo.models.ActivityStep;
import com.kadoo_academy.kadoo.models.Edict;
import com.kadoo_academy.kadoo.models.Step;
import com.kadoo_academy.kadoo.repositories.ActivityStepRepository;
import com.kadoo_academy.kadoo.repositories.EdictRepository;
import com.kadoo_academy.kadoo.repositories.EventRepository;
import com.kadoo_academy.kadoo.repositories.StepRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ActivityStepService {

    @Autowired
    private EdictRepository edictRepository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private ActivityStepRepository activityStepRepository;

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
        step.setActivity(activity);
        activity.setStep(step);

        step.setEvent(null);

        edict.getSteps().add(step);
        edictRepository.save(edict);
    }

    @Transactional
    public void delete(Long stepId) {
        Step step = stepRepository.findById(stepId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Etapa não encontrada."));

        if (step.getActivity() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta etapa não possui atividade para apagar.");
        }
        if (step.getEvent() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta etapa é de evento, não de atividade.");
        }

        ActivityStep activity = step.getActivity();
        step.setActivity(null);


        activityStepRepository.delete(activity);
    }

}
