// ChildProfile.java (Entity)
package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "child_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChildProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "child_id")  // Thêm khóa ngoại chỉ ra mối quan hệ
    private Child child; // Quan hệ One-to-One với Child

    @Transient
    public int getAge() {
        if (birthDate == null) return 0;

        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        return period.getYears();
    }

    // Thêm phương thức để hiển thị ngày sinh định dạng
    @Transient
    public String getFormattedBirthDate() {
        if (birthDate == null) return "N/A";
        return birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // Cập nhật tên của ChildProfile từ tên của Child
    @PrePersist
    @PreUpdate
    public void updateChildProfileName() {
        if (child != null) {
            this.name = child.getName();  // Tên của Child sẽ được sao chép vào ChildProfile
        }
    }


}