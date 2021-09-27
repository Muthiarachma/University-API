package com.example.universitas.repository;

import com.example.universitas.model.entity.DosenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface DosenRepo extends JpaRepository<DosenEntity, String> {

    @Query("SELECT p FROM DosenEntity p WHERE p.fakultas.idFakultas = :idFakultas")
    public List<DosenEntity> findDosenEntityByFakultas (@PathParam("idFakultas") String idFakultas);

    @Query("SELECT p FROM DosenEntity p WHERE p.nip = :nip")
    public DosenEntity findDosenEntityByNip (@PathParam("nip") String nip);

    long countByIdFakultas (String idFaKultas);

}
