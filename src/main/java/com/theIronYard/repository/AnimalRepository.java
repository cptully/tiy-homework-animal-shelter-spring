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
    Page<Animal> findById(Integer id, Pageable pageable);
    Page<Animal> findAll(Pageable pageable);

    List<Animal> findByBreedId(Integer id);

    @Query(value = "SELECT a FROM Animal a WHERE (upper(a.name) LIKE upper(?1))")
    Page<Animal> findByName(String name, Pageable pageable);

    @Query(value = "Select a From Animal a WHERE a.breed.type.id = ?1")
    Page<Animal> findByTypeId(Integer id, Pageable pageable);

    @Query(value = "Select a From Animal a WHERE a.breed.type.id = ?1")
    List<Animal> findByTypeId(Integer id);

    @Query(value = "SELECT a FROM Animal a WHERE " +
            "(?1 = '' OR upper(a.name) LIKE upper(?1))" +
            " AND " +
            "(?2 IS NULL OR a.breed.id = ?2) " +
            "AND " +
            "(?3 IS NULL OR a.breed.type.id = ?3) " +
            "AND " +
            "(?4 IS NULL OR a.id = ?4)")
    Page<Animal> search(String name, Integer breedId, Integer typeId, Integer id, Pageable pageable);
}
