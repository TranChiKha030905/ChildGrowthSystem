package com.example.childgrowth.repository;

import com.example.childgrowth.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}