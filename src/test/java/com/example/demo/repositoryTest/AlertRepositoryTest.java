package com.example.demo.repositoryTest;

import com.example.childgrowthsystem.demo.repositories.AlertRepository;
import com.example.childgrowthsystem.entity.Alerts;
import com.example.childgrowthsystem.entity.ChildProfiles;
import com.example.childgrowthsystem.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    @Test
    @DisplayName("Test lưu và tìm cảnh báo theo childId")
    void testFindByChildId() {
        // Tạo dữ liệu giả
        Users user = new Users();
        user.setUserID(1); // Giả sử có user với ID 1

        ChildProfiles child = new ChildProfiles();
        child.setChildId(1); // Child giả có ID 1
        child.setChildName("Bé A");
        child.setDateOfBirth(LocalDate.of(2020, 1, 1));
        child.setGender(ChildProfiles.Gender.Male);
        child.setUser(user);

        Alerts alert = new Alerts(0, child, "Fever", "Sốt cao 39 độ", LocalDateTime.now());
        alertRepository.save(alert);

        List<Alerts> result = alertRepository.findByChild_ChildID(1);
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getAlertType()).isEqualTo("Fever");
    }
}
