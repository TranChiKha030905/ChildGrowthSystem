package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.Child;
import com.childgrowth.tracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findByParent(User parent);
    List<Child> findByParentAndIsActive(User parent, boolean isActive);
    List<Child> findByNameContainingIgnoreCase(String name);
    List<Child> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
    List<Child> findByParentId(Long parentId);
    List<Child> findByDoctor(User doctor);
} 