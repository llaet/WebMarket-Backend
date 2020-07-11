package com.saleshub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saleshub.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
