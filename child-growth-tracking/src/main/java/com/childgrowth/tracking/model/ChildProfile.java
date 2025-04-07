package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ChildProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String gender; // "Male" hoặc "Female"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User parent;

    // --- GETTER & SETTER ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public User getParent() { return parent; }
    public void setParent(User parent) { this.parent = parent; }
}
