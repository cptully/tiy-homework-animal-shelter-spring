package com.theIronYard.repository;

import com.theIronYard.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chris on 10/2/16.
 */
@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNameEquals(String name);
}
