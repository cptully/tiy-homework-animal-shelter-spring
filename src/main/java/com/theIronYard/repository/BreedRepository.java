package com.theIronYard.repository;

import com.theIronYard.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BreedRepository extends JpaRepository<Breed, Integer> {
    List<Breed> findByTypeName(String typeName);
    List<Breed> findByTypeId(Integer id);

    void deleteById(Integer id);
}
