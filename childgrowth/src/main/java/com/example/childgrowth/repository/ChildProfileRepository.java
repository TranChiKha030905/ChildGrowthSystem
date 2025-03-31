package com.example.childgrowth.repository;

import com.example.childgrowth.model.ChildProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildProfileRepository extends JpaRepository<ChildProfile, Long> {
}