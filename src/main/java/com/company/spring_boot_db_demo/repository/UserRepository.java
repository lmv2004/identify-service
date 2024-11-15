package com.company.spring_boot_db_demo.repository;

import com.company.spring_boot_db_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}