package com.saleshub.domain.dto;

import com.saleshub.domain.City;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<City> cities = new ArrayList<>();

    public StateDTO(String name, List<City> cities){
        this.name = name;
        this.cities = cities;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
