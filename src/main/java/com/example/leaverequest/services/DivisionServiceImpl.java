package com.example.leaverequest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leaverequest.dto.DivisionDTO;
import com.example.leaverequest.models.Division;
import com.example.leaverequest.repositories.DivisionRepository;

@Service
public class DivisionServiceImpl implements DivisionService{
    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public List<Division> getAll() {
        return divisionRepository.findAll();
    }

    @Override
    public Division getById(Integer id) {
        return divisionRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Division Tidak ditemukan"));
    }

    @Override
    public Boolean save(Division division) {
        divisionRepository.save(division);
        return divisionRepository.findById(division.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        divisionRepository.deleteById(id);
        return !divisionRepository.findById(id).isPresent();
    }

    @Override
    public List<DivisionDTO> getAllDivision() {
        List<DivisionDTO> listDivision = new ArrayList<DivisionDTO>();
        
        Boolean a = true;

        if(a == true) {
            List<Division> dataDivision = divisionRepository.findAll();
            dataDivision.forEach((div) -> {
                DivisionDTO dDivisionDTO = new DivisionDTO();

                dDivisionDTO.setId(div.getId());
                dDivisionDTO.setName(div.getName());

                listDivision.add(dDivisionDTO);
            });

            return listDivision;
        }
        
        // return divisionRepository.findAll();
        return null;
    }
    
}
