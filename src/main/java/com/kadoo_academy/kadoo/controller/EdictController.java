package com.kadoo_academy.kadoo.controller;

import com.kadoo_academy.kadoo.dto.request.CreateEdictDto;
import com.kadoo_academy.kadoo.dto.response.ResponseEdictDto;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictActiveDto;
import com.kadoo_academy.kadoo.dto.request.UpdateEdictDto;
import com.kadoo_academy.kadoo.service.EdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@CrossOrigin("*")
@RestController
@RequestMapping("/edict")
public class EdictController {

    @Autowired
    private EdictService edictService;

    @PostMapping
    public ResponseEntity<CreateEdictDto> createEdict(
            @RequestBody CreateEdictDto createEdictDto,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "");
        edictService.createEdict(createEdictDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<Stream<ResponseEdictDto>> getAll(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");

        Stream<ResponseEdictDto> edictList = edictService.edictList(token);
        return ResponseEntity.ok().body(edictList);
    }

    /* @GetMapping("/{id}")
    public ResponseEntity<ResponseEdictDto> getEdictById(@PathVariable ("id") Long id) {
        ResponseEdictDto edictDto = edictService.getEdictById(id);
            return ResponseEntity.ok().body(edictDto);
    } */

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
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateEdictActiveDto> updateEdictActive(@PathVariable ("id") Long id, @RequestBody UpdateEdictActiveDto updateEdictActive){
        edictService.EdictActive(id,updateEdictActive);
        return ResponseEntity.noContent().build();
    }
}
