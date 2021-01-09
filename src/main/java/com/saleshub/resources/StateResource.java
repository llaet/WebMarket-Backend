package com.saleshub.resources;

import com.saleshub.domain.dto.CityDTO;
import com.saleshub.domain.dto.StateDTO;
import com.saleshub.services.CityService;
import com.saleshub.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class StateResource {

    @Autowired
    private StateService service;
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAllOrderedByName(){
        return ResponseEntity.ok(this.service.findAllOrderedByName());
    }

    @GetMapping("/{id}/cidades")
    public ResponseEntity<List<CityDTO>> findCitiesByStateId(@PathVariable("id") Integer stateId){
        return ResponseEntity.ok(this.cityService.findByStateId(stateId));
    }
}
