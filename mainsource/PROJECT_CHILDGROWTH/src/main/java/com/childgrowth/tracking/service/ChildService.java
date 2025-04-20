package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.Child;
import com.childgrowth.tracking.model.User;
import java.time.LocalDate;
import java.util.List;

public interface ChildService {
    Child saveChild(Child child);
    Child getChildById(Long id);
    List<Child> getChildrenByParent(User parent);
    List<Child> getActiveChildrenByParent(User parent);
    List<Child> searchChildrenByName(String name);
    List<Child> getChildrenByBirthDateRange(LocalDate startDate, LocalDate endDate);
    void updateChild(Child child);
    void deactivateChild(Long childId);
    void deleteChild(Long childId);
    boolean isChildLimitReached(User parent);
    void validateChildData(Child child);
    List<Child> getChildrenByDoctor(User doctor);

} 