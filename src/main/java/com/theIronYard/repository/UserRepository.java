package com.theIronYard.repository;

import com.theIronYard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by davehochstrasser on 9/29/16.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
