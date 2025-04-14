package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.model.Child;
import com.childgrowth.tracking.repository.UserRepository;
import com.childgrowth.tracking.service.DoctorService;
import com.childgrowth.tracking.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChildService childService;

    @Override
    public List<User> getAllDoctors() {
        return userRepository.findByRole("ROLE_DOCTOR");
    }

    @Override
    public User getDoctorById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Override
    @Transactional
    public User createDoctor(User doctor) {
        doctor.setRole("ROLE_DOCTOR");
        doctor.setEnabled(false);
        return userRepository.save(doctor);
    }

    @Override
    @Transactional
    public User updateDoctor(Long id, User doctor) {
        User existingDoctor = getDoctorById(id);
        existingDoctor.setFullName(doctor.getFullName());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setPhoneNumber(doctor.getPhoneNumber());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setLicenseNumber(doctor.getLicenseNumber());
        return userRepository.save(existingDoctor);
    }

    @Override
    @Transactional
    public void deleteDoctor(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getPendingDoctors() {
        return userRepository.findByRoleAndEnabled("ROLE_DOCTOR", false);
    }

    @Override
    @Transactional
    public void approveDoctor(Long id) {
        User doctor = getDoctorById(id);
        doctor.setEnabled(true);
        userRepository.save(doctor);
    }

    @Override
    @Transactional
    public void rejectDoctor(Long id) {
        deleteDoctor(id);
    }

    @Override
    public boolean isDoctorApproved(Long id) {
        User doctor = getDoctorById(id);
        return doctor.isEnabled();
    }

    @Override
    public List<Child> getPatientsByDoctor(User doctor) {
        return childService.getChildrenByDoctor(doctor);
    }
} 