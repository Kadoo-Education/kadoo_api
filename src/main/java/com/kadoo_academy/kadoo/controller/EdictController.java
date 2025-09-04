package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateEdictDTO;
import com.kadoo_academy.kadoo.dto.response.EdictDTO;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictActiveDto;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictDto;
import com.kadoo_academy.kadoo.dto.response.EdictDetailsDTO;
import com.kadoo_academy.kadoo.service.EdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/edict")
public class EdictController {

    @Autowired
    private EdictService edictService;

    @PostMapping
    public ResponseEntity<CreateEdictDTO> createEdict(
            @RequestBody CreateEdictDTO createEdictDto,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "");
         edictService.create(createEdictDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEdictDto);
    }
    @GetMapping
    public ResponseEntity<List<EdictDTO>> getAll(){
        List<EdictDTO> edicts = edictService.getAll();
        return ResponseEntity.ok().body(edicts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EdictDetailsDTO> getById(@PathVariable Long id) {
        EdictDetailsDTO edict = edictService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(edict);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEdictById(@PathVariable("id") Long id, @RequestBody UpdateEdictDto updateEdictDto){
        // edictService.updateEdictById(id,updateEdictDto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdictById(@PathVariable ("id") Long id){
        // edictService.deleteEdictById(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateEdictActiveDto> updateEdictActive(@PathVariable ("id") Long id, @RequestBody UpdateEdictActiveDto updateEdictActive){
        // edictService.EdictActive(id,updateEdictActive);
        return ResponseEntity.noContent().build();
    }
}
