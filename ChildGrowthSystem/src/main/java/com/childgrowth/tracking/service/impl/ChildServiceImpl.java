package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.Child;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.ChildRepository;
import com.childgrowth.tracking.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Override
    @Transactional
    public Child saveChild(Child child) {
        validateChildData(child);
        return childRepository.save(child);
    }

    @Override
    public Child getChildById(Long id) {
        return childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child not found with id: " + id));
    }

    @Override
    public List<Child> getChildrenByParent(User parent) {
        return childRepository.findByParent(parent);
    }

    @Override
    public List<Child> getActiveChildrenByParent(User parent) {
        return childRepository.findByParentAndIsActive(parent, true);
    }

    @Override
    public List<Child> searchChildrenByName(String name) {
        return childRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Child> getChildrenByBirthDateRange(LocalDate startDate, LocalDate endDate) {
        return childRepository.findByDateOfBirthBetween(startDate, endDate);
    }

    @Override
    @Transactional
    public void updateChild(Child child) {
        validateChildData(child);
        if (!childRepository.existsById(child.getId())) {
            throw new RuntimeException("Child not found with id: " + child.getId());
        }
        childRepository.save(child);
    }

    @Override
    @Transactional
    public void deactivateChild(Long childId) {
        Child child = getChildById(childId);
        child.setActive(false);
        childRepository.save(child);
    }

    @Override
    @Transactional
    public void deleteChild(Long childId) {
        if (!childRepository.existsById(childId)) {
            throw new RuntimeException("Child not found with id: " + childId);
        }
        childRepository.deleteById(childId);
    }

    @Override
    public boolean isChildLimitReached(User parent) {
        // TODO: Implement based on subscription plan limits
        return false;
    }

    @Override
    public void validateChildData(Child child) {
        if (child.getName() == null || child.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Child name cannot be empty");
        }
        if (child.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        if (child.getParent() == null) {
            throw new IllegalArgumentException("Parent cannot be null");
        }
        if (child.getGender() == null || child.getGender().trim().isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be empty");
        }
    }

    @Override
    public List<Child> getChildrenByDoctor(User doctor) {
        return childRepository.findByDoctor(doctor);
    }
} 