package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class GrowthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double height;
    private Double weight;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private ChildProfile child;

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ChildProfile getChild() { return child; }
    public void setChild(ChildProfile child) { this.child = child; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
