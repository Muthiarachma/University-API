package com.example.universitas.service;

import com.example.universitas.model.entity.DosenEntity;
import com.example.universitas.repository.DosenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.ArrayList;
import java.util.List;

@Service
public class DosenServiceImpl implements DosenService{

    @Autowired
    private DosenRepo dosenRepo;

    @Override
    public DosenEntity saveDosen (DosenEntity dosenEntity){
        return dosenRepo.save(dosenEntity);
    }

    @Override
    public List<DosenEntity> getAllDosen(){
        List<DosenEntity> dosenEntityList = new ArrayList<>();
        dosenRepo.findAll().forEach(dosenEntityList::add);
        return dosenEntityList;
    }

    @Override
    public DosenEntity findDosenId (String idDosen){
        return dosenRepo.findById(idDosen).get();
    }

    @Override
    public DosenEntity findDosenNip (String nip){
        return dosenRepo.findDosenEntityByNip (nip);
    }

    @Override
    public long countDosenbyFakultas (String idFakultas){
        return dosenRepo.countByIdFakultas(idFakultas);
    }

    @Override
    public List<DosenEntity> findDosenbyFakultas (String idFakultas){
        return dosenRepo.findDosenEntityByFakultas(idFakultas);
    }
}
