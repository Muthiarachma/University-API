package com.example.universitas.service;

import com.example.universitas.exception.ResourceNotFoundException;
import com.example.universitas.model.dto.JurusanDto;
import com.example.universitas.model.entity.JurusanEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface JurusanService {

    List<JurusanEntity> getAllJurusan();
    JurusanEntity getJurusanById(String id) throws ResourceNotFoundException;
    void delJurusanById(String id)throws ResourceNotFoundException ;
    List<JurusanEntity> getJurusanByFak(String idFakultas);
    JurusanEntity saveJurusan(JurusanEntity jurusanEntity);
    JurusanEntity updateJurusan(String idJurusan, JurusanEntity jurusanDetails)throws ResourceNotFoundException;
    Object countJurByFak(String idFakultas);

}
