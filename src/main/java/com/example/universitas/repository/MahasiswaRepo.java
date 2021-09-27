package com.example.universitas.repository;

import com.example.universitas.model.entity.MahasiswaEntity;
import com.example.universitas.model.projection.MahasiswaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MahasiswaRepo extends JpaRepository<MahasiswaEntity, String> {

    @Query("SELECT mahasiswa FROM MahasiswaEntity mahasiswa")
    List<MahasiswaProjection> findAllProjection();

    MahasiswaProjection findByIdMahasiswa(String idMahasiswa);

    @Query("SELECT COUNT(m.idMahasiswa) as jumlahMahasiswa FROM MahasiswaEntity m WHERE m.idFakultas=?1")
    Object countByIdFakultas(String idFakultas);

}
