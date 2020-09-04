package com.saleshub.repositories;

import com.saleshub.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saleshub.domain.SaleOrder;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer> {

    @Transactional(readOnly = true)
    Page<SaleOrder> findByCustomer(Customer customer, Pageable pageable);
}
