package com.example.universitas.service;

import com.example.universitas.model.entity.FakultasEntity;

import java.util.List;

public interface FakultasService {

    List<FakultasEntity> getAllFakultas();

    FakultasEntity getByFakultasId(String id);

    FakultasEntity saveFakultas(FakultasEntity fakultasEntity);

    Object countMahasiswaDosenByIdFakultas(String idFakultas);

}
