package com.saleshub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saleshub.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
