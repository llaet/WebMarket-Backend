package com.saleshub.services;

import com.saleshub.domain.State;
import com.saleshub.domain.dto.StateDTO;
import com.saleshub.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {

    @Autowired
    private StateRepository repository;

    public List<StateDTO> findAllOrderedByName(){
        List<State> states = this.repository.findAllByOrderByName();

        //returns a DTO list of states
        return states.stream().map(state -> new StateDTO(state.getName(), state.getCities()))
                .collect(Collectors.toList());
    }
}
