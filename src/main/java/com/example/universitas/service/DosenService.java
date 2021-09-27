package com.example.universitas.service;

import com.example.universitas.model.entity.DosenEntity;

import java.util.List;

public interface DosenService {

    public List<DosenEntity> getAllDosen();
    public DosenEntity findDosenId (String idDosen);
    public DosenEntity findDosenNip (String nip);
    public DosenEntity saveDosen (DosenEntity dosenEntity);
    public long countDosenbyFakultas (String idFakultas);
    public List<DosenEntity> findDosenbyFakultas (String idFakultas);
}
