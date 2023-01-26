package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leaverequest.models.Region;
import com.example.leaverequest.repositories.RegionRepository;

@Service
public class RegionServiceImpl implements RegionService{
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Region> getAll(){
        return regionRepository.findAll();
    }

    @Override
    public Region getById(Integer id) {
        return regionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Region tidak ditemukan"));
    }

    @Override
    public Boolean save(Region region){
        regionRepository.save(region);
        return regionRepository.findById(region.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id){
        regionRepository.deleteById(id);
        return !regionRepository.findById(id).isPresent();
    }

    
}
