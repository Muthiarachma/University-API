package com.example.universitas.repository;

import com.example.universitas.model.entity.FakultasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FakultasRepo extends JpaRepository<FakultasEntity, String> {
}
