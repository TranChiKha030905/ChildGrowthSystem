package com.example.childgrowthsystem.demo.repositories;

import com.example.childgrowthsystem.entity.Memberships;
import com.example.childgrowthsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Memberships, Integer>
{
    // Tìm gói thành viên theo User
    Optional<Memberships> findByUser(Users user);
}
