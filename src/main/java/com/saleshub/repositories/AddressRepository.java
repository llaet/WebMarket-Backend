package com.saleshub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saleshub.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
