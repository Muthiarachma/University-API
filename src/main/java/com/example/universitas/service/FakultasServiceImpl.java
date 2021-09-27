package com.example.universitas.service;

import com.example.universitas.model.entity.FakultasEntity;
import com.example.universitas.repository.DosenRepo;
import com.example.universitas.repository.FakultasRepo;
import com.example.universitas.repository.MahasiswaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakultasServiceImpl implements FakultasService {

    @Autowired
    public FakultasRepo fakultasRepo;

    @Autowired
    private MahasiswaRepo mahasiswaRepo;

    @Autowired
    private DosenRepo dosenRepo;

    @Override
    public List<FakultasEntity> getAllFakultas(){
        List<FakultasEntity> fakultasEntityList = new ArrayList<>();
        fakultasRepo.findAll().forEach(fakultasEntityList::add);
        return fakultasEntityList;
    }

    @Override
    public FakultasEntity getByFakultasId(String id){
        return fakultasRepo.findById(id).get();
    }

    @Override
    public FakultasEntity saveFakultas(FakultasEntity fakultasEntity){

        return fakultasRepo.save(fakultasEntity);

    }

    @Override
    public Object countMahasiswaDosenByIdFakultas(String idFakultas){
        Object jumlahMahasiswa = mahasiswaRepo.countByIdFakultas(idFakultas);
        Long jumlahDosen = dosenRepo.countByIdFakultas(idFakultas);
        String jumlahKeseluhuran = (idFakultas + "\nJumlah Mahasiswa: " + jumlahMahasiswa + "\nJumlah Dosen: " + jumlahDosen);
        return jumlahKeseluhuran;
    }
}
