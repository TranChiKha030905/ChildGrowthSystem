package com.example.childgrowthsystem.reponsitory;

import com.example.childgrowthsystem.entity.Users;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userReponsitory extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
