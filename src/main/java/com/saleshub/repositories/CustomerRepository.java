package com.saleshub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saleshub.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
