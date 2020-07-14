package com.saleshub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saleshub.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
