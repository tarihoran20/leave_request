package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Region;

public interface RegionService {
    public List<Region> getAll();
    public Region getById(Integer id);
    public Boolean save(Region region);
    public Boolean delete (Integer id);
}
