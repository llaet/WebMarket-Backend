package com.saleshub.resources;

import com.saleshub.domain.dto.StateDTO;
import com.saleshub.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class StateResource {

    @Autowired
    private StateService service;

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAllOrderedByName(){
        return ResponseEntity.ok(this.service.findAllOrderedByName());
    }
}
