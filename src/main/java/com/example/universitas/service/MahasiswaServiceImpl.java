package com.example.universitas.service;

import com.example.universitas.model.entity.MahasiswaEntity;
import com.example.universitas.model.projection.MahasiswaProjection;
import com.example.universitas.repository.MahasiswaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService{

    @Autowired
    private MahasiswaRepo mahasiswaRepo;

    @Override
    public List<MahasiswaProjection> getAllMahasiswa() {
        return mahasiswaRepo.findAllProjection();
    }

    @Override
    public MahasiswaProjection getMahasiswaById(String idMahasiswa) {
        return mahasiswaRepo.findByIdMahasiswa(idMahasiswa);
    }

    @Override
    public MahasiswaEntity saveMahasiswa(MahasiswaEntity mahasiswaEntity) {
        return mahasiswaRepo.save(mahasiswaEntity);
    }

    @Override
    public Object countMahasiswaByIdFakultas(String idFakultas) {
        return mahasiswaRepo.countByIdFakultas(idFakultas);
    }
}
