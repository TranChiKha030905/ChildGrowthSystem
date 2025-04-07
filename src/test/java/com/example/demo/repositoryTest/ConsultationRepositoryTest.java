package com.example.demo.repositoryTest;

import com.example.childgrowthsystem.demo.repositories.ConsultationRepository;
import com.example.childgrowthsystem.entity.ChildProfiles;
import com.example.childgrowthsystem.entity.Consultations;
import com.example.childgrowthsystem.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ConsultationRepositoryTest {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Test
    @DisplayName("Test save and find by user ID")
    void testFindByUserUserID() {
        Users user = new Users();
        user.setUserID(1);

        Users doctor = new Users();
        doctor.setUserID(2);

        ChildProfiles child = new ChildProfiles();
        child.setChildId(1);

        Consultations consultation = new Consultations();
        consultation.setUser(user);
        consultation.setDoctor(doctor);
        consultation.setChild(child);
        consultation.setRequestDate(LocalDateTime.now());
        consultation.setStatus(Consultations.Status.Pending);
        consultation.setMessage("Cần tư vấn sốt cao");

        consultationRepository.save(consultation);

        List<Consultations> results = consultationRepository.findByUser_UserID(1);
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getMessage()).contains("sốt");
    }
}
