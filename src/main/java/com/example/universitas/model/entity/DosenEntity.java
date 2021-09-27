package com.example.universitas.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dosen")
public class DosenEntity{

    @Id
    @GeneratedValue(generator = "dsn-generator")
    @GenericGenerator(name = "dsn-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "DSN"),
            strategy = "com.example.universitas.identifier.MyGenerator"
    )
    @Column(name = "id_dosen")
    private String idDosen;

    @Column(name = "nama_dosen")
    private String namaDosen;

    @Column(name = "nip", length = 18, unique = true)
    private String nip;

//    @ManyToOne
//    private JurusanEntity jurusan;

    @Column(name = "id_fakultas")
    private String idFakultas;
    @ManyToOne
    @JoinColumn(name = "id_fakultas", insertable = false, updatable = false)
    private FakultasEntity fakultas;

}
