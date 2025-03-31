package com.example.childgrowthsystem.demo.repositories;

import com.example.childgrowthsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer>
{
    Optional<Users> findByEmail(String email);
}
