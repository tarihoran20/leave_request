package com.example.leaverequest.services;

import java.util.List;

import com.example.leaverequest.models.Region;

public interface RegionService {
    public List<Region> getAll();
    public Region getById(Integer id);
    public Boolean save(Region region);
    public Boolean delete (Integer id);
}
