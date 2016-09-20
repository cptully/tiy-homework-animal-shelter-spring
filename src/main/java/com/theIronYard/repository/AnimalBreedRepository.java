package com.theIronYard.repository;

import com.theIronYard.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalBreedRepository extends JpaRepository<Breed, Integer> {
}
