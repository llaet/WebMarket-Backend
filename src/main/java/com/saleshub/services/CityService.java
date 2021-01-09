package com.saleshub.services;

import com.saleshub.domain.City;
import com.saleshub.domain.dto.CityDTO;
import com.saleshub.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public List<CityDTO> findAll(){
        List<City> cities = this.repository.findAll();

        //return a list of cities DTO
        return cities.stream().map(city -> new CityDTO(city.getName(), city.getState()))
                .collect(Collectors.toList());
    }
}
