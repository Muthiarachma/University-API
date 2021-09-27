package com.example.universitas.controller;


import com.example.universitas.exception.ResourceNotFoundException;
import com.example.universitas.model.dto.JurusanDto;
import com.example.universitas.model.entity.JurusanEntity;
import com.example.universitas.service.JurusanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/jurusan")
public class JurusanController {

    @Autowired
    public JurusanService jurusanService;

    @Autowired
    ModelMapper modelMapper;

    private JurusanDto convertToDto(JurusanEntity jurusanEntity){
        JurusanDto jurusanDto = modelMapper.map(jurusanEntity, JurusanDto.class);
        return jurusanDto;
    }

    private JurusanEntity convertToEntity(JurusanDto jurusanDto){
        JurusanEntity jurusanEntity = modelMapper.map(jurusanDto, JurusanEntity.class);
        return jurusanEntity;
    }

    //get all jurusan
    @GetMapping()
    public List<JurusanDto> showAllJurusan() {
        List<JurusanEntity> jurusanEntities = jurusanService.getAllJurusan();
        List<JurusanDto> jurusanDtos = jurusanEntities.stream().map(this::convertToDto).collect(Collectors.toList());
        return jurusanDtos;
    }

    //get jurusan by id
    @RolesAllowed({"admin", "user"})
    @GetMapping("/{id}")
    public JurusanDto showJurusanById(@PathVariable(value="id") String jurusanId) throws ResourceNotFoundException {
        JurusanEntity jurusanEntity = jurusanService.getJurusanById(jurusanId);
        JurusanDto jurusanDto = convertToDto(jurusanEntity);
        return jurusanDto;
    }

    //get jurusan by id fakultas
    @RolesAllowed({"admin", "user"})
    @GetMapping(value = "/kodefak")
    public List<JurusanDto> showJurusanByKodeFak(@RequestParam("fak") String fak) {
        List<JurusanEntity> jurusanEntities = jurusanService.getJurusanByFak(fak);
        return jurusanEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //add jurusan
    @RolesAllowed("admin")
    @PostMapping
    public JurusanDto insertJurusan(@RequestBody JurusanDto jurusanDto){
        JurusanEntity jurusanEntity = convertToEntity(jurusanDto);
        JurusanEntity jurusanUpdate = jurusanService.saveJurusan(jurusanEntity);
        return convertToDto(jurusanUpdate);
    }

    //delete jurusan
    @RolesAllowed("admin")
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteJurusan(@PathVariable(value = "id") String jurusanId)throws ResourceNotFoundException {
        jurusanService.delJurusanById(jurusanId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete jurusan", Boolean.TRUE);
        return response;
    }

    // update jurusan
    @RolesAllowed("admin")
    @PutMapping("/{id}")
    public JurusanDto updateJurusan(@PathVariable(value="id") String jurusanId, @RequestBody JurusanDto jurusanDto) throws ResourceNotFoundException {
        JurusanEntity jurusanEntity = convertToEntity(jurusanDto);
        JurusanEntity jurusanUpdate = jurusanService.updateJurusan(jurusanId, jurusanEntity);
        return convertToDto(jurusanUpdate);
    }

    @GetMapping("/fak/{fak}")
    public Object countJurusan(@PathVariable(value = "fak") String fak){

        return jurusanService.countJurByFak(fak);
    }
}
