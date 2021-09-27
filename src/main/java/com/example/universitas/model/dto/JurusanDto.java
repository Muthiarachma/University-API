package com.example.universitas.model.dto;

import com.example.universitas.model.entity.FakultasEntity;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class JurusanDto {

    private String idJurusan;
    private String namaJurusan;
    private String kodeJurusan;
    private String idFakultas;
    @ManyToOne
    @JoinColumn(
            name = "id_fakultas",
            referencedColumnName = "id_fakultas",
            insertable = false,
            updatable = false
    )
    private FakultasEntity fakultasEntity;

}
