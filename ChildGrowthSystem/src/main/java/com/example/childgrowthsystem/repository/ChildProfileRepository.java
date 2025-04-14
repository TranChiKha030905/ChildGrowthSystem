// ChildProfileRepository.java
package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.ChildProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChildProfileRepository extends JpaRepository<ChildProfile, Long> {
    List<ChildProfile> findByUserId(Long userId);
}