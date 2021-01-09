package com.saleshub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saleshub.domain.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

    List<City> findByStateId(Integer stateId);
}
