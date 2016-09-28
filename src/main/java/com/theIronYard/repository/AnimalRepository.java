package com.theIronYard.repository;

import com.theIronYard.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Page<Animal> findByBreedId(Integer id, Pageable pageable);
    List<Animal> findByBreedId(Integer id);
    Page<Animal> findByName(String name, Pageable pageable);
    @Query(value = "Select a From Animal a WHERE a.breed.type.id = ?1")
    Page<Animal> findByTypeId(Integer id, Pageable pageable);
    Page<Animal> findById(Integer id, Pageable pageable);
}
