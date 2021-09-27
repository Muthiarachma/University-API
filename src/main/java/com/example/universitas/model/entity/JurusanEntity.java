package com.example.universitas.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "jurusan")
public class JurusanEntity {

    @Id
    @GeneratedValue(generator = "jur-generator")
    @GenericGenerator(name = "jur-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "PRODI"),
            strategy = "com.example.universitas.identifier.MyGenerator"
    )
    @Column(name = "id_jurusan")
    private String idJurusan;

    @Column(name = "nama_jurusan")
    private String namaJurusan;

    @Column(name = "kode_jurusan", unique = true)
    private String kodeJurusan;

    @Column(name = "id_fakultas")
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
