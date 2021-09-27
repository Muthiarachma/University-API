package com.example.universitas.repository;

import com.example.universitas.model.entity.JurusanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JurusanRepo extends JpaRepository<JurusanEntity, String> {

    @Query (
            "SELECT j " +
                    "FROM JurusanEntity  AS j " +
                    "WHERE j.idFakultas = :idFakultas")
    List<JurusanEntity> findByFak(String idFakultas);

    @Query ("SELECT COUNT(j.namaJurusan) " +
            "FROM JurusanEntity AS j " +
            "WHERE j.idFakultas = :idFakultas")
    Object countJurByFak(String idFakultas);
}
