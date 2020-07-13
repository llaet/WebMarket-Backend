package com.saleshub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saleshub.domain.City;

public interface CityRepository extends JpaRepository<City, Integer>{

}
