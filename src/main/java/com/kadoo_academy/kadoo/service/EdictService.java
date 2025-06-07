package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.CreateEdictDto;
import com.kadoo_academy.kadoo.dto.ResponseEdictDto;
import com.kadoo_academy.kadoo.dto.UpdateEdictDto;
import com.kadoo_academy.kadoo.exceptions.EdictExistsException;
import com.kadoo_academy.kadoo.exceptions.EdictNotFoundException;
import com.kadoo_academy.kadoo.models.Edict;
import com.kadoo_academy.kadoo.repository.EdictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;


@Service
public class EdictService {
    @Autowired
    private EdictRepository edictRepository;

    public CreateEdictDto createEdict(CreateEdictDto createEdictDto){
        Edict entity = new Edict();
        entity.setTitle(createEdictDto.title());
        entity.setDescription(createEdictDto.description());
        entity.setLinkDoc(createEdictDto.linkDoc());
        entity.setEndDate(createEdictDto.endDate());
        entity.setStartDate(createEdictDto.startDate());
        edictRepository.save(entity);
        return createEdictDto;
    }
    public Stream<ResponseEdictDto> edictList(){
        List<Edict> entity = edictRepository.findAll();
        return entity.stream().map(edict -> new ResponseEdictDto(
                edict.getId(),edict.getTitle(),edict.getDescription(),
                edict.getLinkDoc(),edict.getStartDate(),
                edict.getEndDate(),edict.isActive()));
    }
    public Edict getEdictById(Long id){
        return edictRepository.findById(id).orElseThrow(() -> new EdictExistsException("Edict already exists"));
    }

    public void updateEdictById(Long id, UpdateEdictDto updateEdictDto){
        Edict edictEntity = edictRepository.findById(id).orElseThrow(() -> new EdictNotFoundException("Edict not found"));
                edictEntity.setTitle(updateEdictDto.title());
                edictEntity.setDescription(updateEdictDto.description());
                edictEntity.setLinkDoc(updateEdictDto.linkDoc());
                edictEntity.setStartDate(updateEdictDto.startDate());
                edictEntity.setEndDate(updateEdictDto.endDate());
                edictEntity.setActive(updateEdictDto.active());

            edictRepository.save(edictEntity);
        }

    public void deleteEdictById(Long id){
        boolean idExists = edictRepository.existsById(id);
        if (idExists){
            edictRepository.deleteById(id);
        } else {
            throw new EdictExistsException("Edict already exists");
        }
    }
}

