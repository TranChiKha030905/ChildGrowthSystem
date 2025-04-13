package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.model.Child;
import java.util.List;

public interface DoctorService {
    List<User> getAllDoctors();
    User getDoctorById(Long id);
    User createDoctor(User doctor);
    User updateDoctor(Long id, User doctor);
    void deleteDoctor(Long id);
    List<User> getPendingDoctors();
    void approveDoctor(Long id);
    void rejectDoctor(Long id);
    boolean isDoctorApproved(Long id);
    List<Child> getPatientsByDoctor(User doctor);
} 