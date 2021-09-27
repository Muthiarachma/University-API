package com.example.universitas.controller;

import com.example.universitas.model.dto.MahasiswaDto;
import com.example.universitas.model.entity.MahasiswaEntity;
import com.example.universitas.model.projection.MahasiswaProjection;
import com.example.universitas.service.MahasiswaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/mahasiswa")
public class MahasiswaController {

    // Auto wiring
    @Autowired
    private MahasiswaServiceImpl mahasiswaService;

    @Autowired
    private ModelMapper modelMapper;

    // Mapping section
    @RolesAllowed("admin")
    @PostMapping
    public MahasiswaDto saveMahasiswa(@RequestBody MahasiswaEntity mahasiswaEntity) {
        return modelMapper.map(mahasiswaService.saveMahasiswa(mahasiswaEntity), MahasiswaDto.class);
    }

    @GetMapping
    public List<MahasiswaProjection> getAllMahasiswa() {
        return mahasiswaService.getAllMahasiswa();
    }

    @RolesAllowed({"admin", "user"})
    @GetMapping("/{id}")
    public MahasiswaProjection getMahasiswaById(@PathVariable("id") String idMahasiswa) {
        return mahasiswaService.getMahasiswaById(idMahasiswa);
    }

    @GetMapping("jumlah/{id}")
    public Object countMahasiswaByIdFakultas(@PathVariable("id") String idFakultas) {
        return mahasiswaService.countMahasiswaByIdFakultas(idFakultas);
    }
}
