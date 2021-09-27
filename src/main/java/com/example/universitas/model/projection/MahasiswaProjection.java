package com.example.universitas.model.projection;

import org.springframework.beans.factory.annotation.Value;

public interface MahasiswaProjection {

    String getIdMahasiswa();

    String getNamaMahasiswa();

    String getNim();

    @Value("#{target.fakultasEntity.namaFakultas}")
    String getNamaFakultas();

}
