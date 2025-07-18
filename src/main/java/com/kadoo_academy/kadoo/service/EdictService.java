package com.kadoo_academy.kadoo.service;

import com.kadoo_academy.kadoo.dto.request.CreateEdictDto;
import com.kadoo_academy.kadoo.dto.response.ProfileUserResponseDTO;
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
import com.kadoo_academy.kadoo.repositories.UserEdictRepository;
import com.kadoo_academy.kadoo.repositories.UserRepository;
import com.kadoo_academy.kadoo.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class EdictService {
    @Autowired
    private EdictRepository edictRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEdictRepository userEdictRepository;

    @Autowired
    private TokenService tokenService;

    public CreateEdictDto createEdict(CreateEdictDto createEdictDto, String token){

        ProfileUserResponseDTO profile = tokenService.decodeToken(token);

        User user = new User();
        user.setId(profile.id());
        /*if(user.getType() != UserEnum.ADMIN &&
        user.getType() != UserEnum.ENTERPRISE){
            throw new ProfileNotAuthorizedException("Porfile is not authorized");
        }*/
        Edict entity = new Edict();
        entity.setTitle(createEdictDto.title());
        entity.setDescription(createEdictDto.description());
        entity.setLinkDoc(createEdictDto.linkDoc());
        entity.setEndDate(createEdictDto.endDate());
        entity.setStartDate(createEdictDto.startDate());
        entity.setTags(createEdictDto.tags());
        entity.setUser(user);


        edictRepository.save(entity);
        return createEdictDto;
    }
    public Stream<ResponseEdictDto> edictList(String token){
        ProfileUserResponseDTO userData = tokenService.decodeToken(token);
        Long userId = userData.id();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Lista de inscrições do usuário
        Set<Long> subscribedEdictIds = userEdictRepository.findByUserSubscribe(user).stream()
                .map(ue -> ue.getEdict().getId())
                .collect(Collectors.toSet());

        // Mapeando todos os editais com info de inscrição
        return edictRepository.findAll().stream().map(edict ->
                new ResponseEdictDto(
                        edict.getId(),
                        edict.getTitle(),
                        edict.getDescription(),
                        edict.getLinkDoc(),
                        edict.getStartDate(),
                        edict.getEndDate(),
                        edict.isActive(),
                        edict.getTags(),
                        subscribedEdictIds.contains(edict.getId()) // aqui está o segredo
                )
        );
    }
    /* public ResponseEdictDto getEdictById(Long id){
        Edict edict = edictRepository.findById(id).orElseThrow(() -> new EdictExistsException("Edict doesn't exist"));
        return new ResponseEdictDto(
                edict.getId(),edict.getTitle(),edict.getDescription(),
                edict.getLinkDoc(), edict.getStartDate(),
                edict.getEndDate(),edict.isActive(),edict.getTags());
    }*/

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

