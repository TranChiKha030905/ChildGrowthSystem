package com.example.childgrowth.service;

import com.example.childgrowth.model.Child;
import com.example.childgrowth.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    public Child saveChild(Child child) {
        // Tính BMI: BMI = cân nặng (kg) / (chiều cao (m) * chiều cao (m))
        double heightInMeters = child.getHeight() / 100; // Chuyển cm sang m
        double bmi = child.getWeight() / (heightInMeters * heightInMeters);
        child.setBmi(bmi);
        return childRepository.save(child);
    }
}