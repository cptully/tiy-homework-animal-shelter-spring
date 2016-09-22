package com.theIronYard.repository;

import com.theIronYard.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {
    List<Breed> findByTypeName(String typeName);

    void deleteById(Integer id);

}
