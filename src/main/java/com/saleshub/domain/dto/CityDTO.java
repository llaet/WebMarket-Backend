package com.saleshub.domain.dto;

import com.saleshub.domain.State;

import java.io.Serializable;
import java.util.List;

public class CityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private State state;

    public CityDTO(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
