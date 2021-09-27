package com.example.universitas.service;

import com.example.universitas.model.entity.MahasiswaEntity;
import com.example.universitas.model.projection.MahasiswaProjection;

import java.util.List;

public interface MahasiswaService {

    List<MahasiswaProjection> getAllMahasiswa();

    MahasiswaProjection getMahasiswaById(String idMahasiswa);

    MahasiswaEntity saveMahasiswa(MahasiswaEntity mahasiswaEntity);

    Object countMahasiswaByIdFakultas(String idFakultas);
}
