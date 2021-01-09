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

    public List<CityDTO> findByStateId(Integer stateId){
        List<City> cities = this.repository.findByStateId(stateId);
        //return a list of cities DTO
        return cities.stream().map(city -> new CityDTO(city.getId(), city.getName()))
                .collect(Collectors.toList());
    }
}
