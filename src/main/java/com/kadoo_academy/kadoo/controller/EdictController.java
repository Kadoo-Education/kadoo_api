package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.CreateEdictDto;
import com.kadoo_academy.kadoo.dto.ResponseEdictDto;
import com.kadoo_academy.kadoo.dto.UpdateEdictDto;
import com.kadoo_academy.kadoo.models.Edict;
import com.kadoo_academy.kadoo.service.EdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;


@RestController
@RequestMapping("/api/edict")
public class EdictController {

    @Autowired
    private EdictService edictService;

    @PostMapping
    public ResponseEntity<CreateEdictDto> createEdict(@RequestBody CreateEdictDto createEdictDto){
         edictService.createEdict(createEdictDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<Stream<ResponseEdictDto>> responseEntity(){
      Stream<ResponseEdictDto> edictList = edictService.edictList();
     return ResponseEntity.ok().body(edictList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEdictDto> getEdictById(@PathVariable ("id") Long id) {
        Edict edictById = edictService.getEdictById(id);
            ResponseEdictDto edictDto = new ResponseEdictDto(
                    edictById.getId(),edictById.getTitle(),edictById.getDescription(),
                    edictById.getLinkDoc(), edictById.getStartDate(),
                    edictById.getEndDate(), edictById.isActive());
            return ResponseEntity.ok().body(edictDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEdictById(@PathVariable("id") Long id, @RequestBody UpdateEdictDto updateEdictDto){
        edictService.updateEdictById(id,updateEdictDto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdictById(@PathVariable ("id") Long id){
        edictService.deleteEdictById(id);
        return ResponseEntity.noContent().build();
    }
}
