package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateEdictDto;
import com.kadoo_academy.kadoo.dto.response.ResponseEdictDto;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictActiveDto;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictDto;
import com.kadoo_academy.kadoo.exceptions.customExceptions.EdictExistsException;
import com.kadoo_academy.kadoo.exceptions.customExceptions.EdictNotFoundException;
import com.kadoo_academy.kadoo.exceptions.customExceptions.ProfileNotAuthorizedException;
import com.kadoo_academy.kadoo.models.Edict;
import com.kadoo_academy.kadoo.models.User;
import com.kadoo_academy.kadoo.models.enums.UserEnum;
import com.kadoo_academy.kadoo.repositories.EdictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Stream;


@Service
public class EdictService {
    @Autowired
    private EdictRepository edictRepository;

    public CreateEdictDto createEdict(CreateEdictDto createEdictDto){
        User user = new User();
        if(user.getType() != UserEnum.ADMIN &&
        user.getType() != UserEnum.ENTERPRISE){
            throw new ProfileNotAuthorizedException("Porfile is not authorized");
        }
        Edict entity = new Edict();
        entity.setTitle(createEdictDto.title());
        entity.setCategory(createEdictDto.category());
        entity.setDescription(createEdictDto.description());
        entity.setLinkDoc(createEdictDto.linkDoc());
        entity.setEndDate(createEdictDto.endDate());
        entity.setStartDate(createEdictDto.startDate());
        entity.setTag(createEdictDto.tag());
        edictRepository.save(entity);
        return createEdictDto;
    }
    public Stream<ResponseEdictDto> edictList(){
        List<Edict> entity = edictRepository.findAll();
        return entity.stream().map(edict -> new ResponseEdictDto(
                edict.getId(),edict.getTitle(), edict.getCategory(),
                edict.getDescription(),edict.getLinkDoc(),edict.getStartDate(),
                edict.getEndDate(),edict.isActive(),edict.getTag()));
    }
    public ResponseEdictDto getEdictById(Long id){
        Edict edict = edictRepository.findById(id).orElseThrow(() -> new EdictExistsException("Edict doesn't exist"));
        return new ResponseEdictDto(
                edict.getId(),edict.getTitle(), edict.getCategory(),edict.getDescription(),
                edict.getLinkDoc(), edict.getStartDate(),
                edict.getEndDate(),edict.isActive(),edict.getTag());
    }

    public void updateEdictById(Long id, UpdateEdictDto updateEdictDto){
        Edict edictEntity = edictRepository.findById(id).orElseThrow(() -> new EdictNotFoundException("Edict not found"));
                edictEntity.setTitle(updateEdictDto.title());
                edictEntity.setCategory(updateEdictDto.category());
                edictEntity.setDescription(updateEdictDto.description());
                edictEntity.setLinkDoc(updateEdictDto.linkDoc());
                edictEntity.setStartDate(updateEdictDto.startDate());
                edictEntity.setEndDate(updateEdictDto.endDate());
                edictEntity.setActive(updateEdictDto.active());

            edictRepository.save(edictEntity);
        }

    public void deleteEdictById(Long id){
        boolean idExists = edictRepository.existsById(id);
        if (!idExists){
            throw new EdictExistsException("Edict doesn't exist");
        }
        edictRepository.deleteById(id);
    }
    public void EdictActive(Long id, UpdateEdictActiveDto updateEdict){
     Edict edict = edictRepository.findById(id).orElseThrow(()-> new EdictExistsException("Edict not found"));
     edict.setActive(updateEdict.getActive());
     edictRepository.save(edict);
    }
}

