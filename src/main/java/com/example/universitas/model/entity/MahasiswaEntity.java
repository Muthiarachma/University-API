package com.example.universitas.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mahasiswa")
public class MahasiswaEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "MHS"),
            strategy = "com.example.universitas.identifier.MyGenerator"
    )
    private String idMahasiswa;

    private String namaMahasiswa;

    @Column(name = "nomor_induk_mahasiswa", unique = true, length = 12)
    private String nim;

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
