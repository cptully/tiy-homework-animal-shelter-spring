package com.theIronYard.repository;

import com.theIronYard.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TypeRepository extends JpaRepository<Type, Integer> {
    void deleteById(Integer id);
    Type findById(Integer id);
}
