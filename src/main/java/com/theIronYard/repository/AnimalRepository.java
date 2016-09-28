package com.theIronYard.repository;

import com.theIronYard.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findByBreedId(Integer id);
    List<Animal> findByName(String name);


    // find all animals whose breed matched the given typeID
//    Page<Animal> findBy
}
