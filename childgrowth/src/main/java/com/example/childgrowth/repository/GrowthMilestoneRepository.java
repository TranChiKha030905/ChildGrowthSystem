package com.example.childgrowth.repository;

import com.example.childgrowth.model.GrowthMilestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrowthMilestoneRepository extends JpaRepository<GrowthMilestone, Long> {
}