package com.example.universitas.service;

import com.example.universitas.exception.ResourceNotFoundException;
import com.example.universitas.model.dto.JurusanDto;
import com.example.universitas.model.entity.JurusanEntity;
import com.example.universitas.repository.JurusanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JurusanServiceImpl implements JurusanService {

    @Autowired
    public JurusanRepo jurusanRepo;

    //------------------------- Service Jurusan ----------------------------
    @Override
    public List<JurusanEntity> getAllJurusan() {
        List<JurusanEntity> jurusanEntities = new ArrayList<>();
        jurusanRepo.findAll().forEach(jurusanEntities::add);
        return jurusanEntities;
    }

    @Override
    public JurusanEntity getJurusanById(String idJurusan) throws ResourceNotFoundException {
        jurusanRepo.findById(idJurusan)
                .orElseThrow(() -> new ResourceNotFoundException("Jurusan not found for this id = " + idJurusan));
        JurusanEntity jurusanEntity = jurusanRepo.findById(idJurusan).get();
        return jurusanEntity;
    }

    public void delJurusanById(String idJurusan) throws ResourceNotFoundException {
        jurusanRepo.findById(idJurusan)
                .orElseThrow(() -> new ResourceNotFoundException("Jurusan not found for this id = " + idJurusan));
        jurusanRepo.deleteById(idJurusan);
    }

    @Override
    public List<JurusanEntity> getJurusanByFak(String idFakultas) {
        List<JurusanEntity> jurusanEntities;
        jurusanEntities = jurusanRepo.findByFak(idFakultas);
        return jurusanEntities;
    }

    @Override
    public JurusanEntity saveJurusan(JurusanEntity jurusanEntity) {

        return jurusanRepo.save(jurusanEntity);
    }

    @Override
    public JurusanEntity updateJurusan(String idJurusan, JurusanEntity jurusanDetails) throws ResourceNotFoundException {

        JurusanEntity jurusanEntity = jurusanRepo.findById(idJurusan)
                .orElseThrow(() -> new ResourceNotFoundException("Jurusan not found for this id :: " + idJurusan));
        jurusanEntity.setNamaJurusan(jurusanDetails.getNamaJurusan());
        jurusanEntity.setKodeJurusan(jurusanDetails.getKodeJurusan());
        jurusanEntity.setIdFakultas(jurusanDetails.getIdFakultas());
        final JurusanEntity updatedJurusan = jurusanRepo.save(jurusanEntity);
        return updatedJurusan;
    }

    @Override
    public Object countJurByFak(String idFakultas) {
        Object jumlah = jurusanRepo.countJurByFak(idFakultas);
        return jumlah;
    }
}
