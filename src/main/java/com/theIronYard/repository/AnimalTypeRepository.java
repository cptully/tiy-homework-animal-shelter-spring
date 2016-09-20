package com.theIronYard.repository;

import com.theIronYard.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalTypeRepository extends JpaRepository<Type, Integer> {
}
