package com.example.demo;

import com.example.childgrowthsystem.demo.repositories.ConsultationRepository;
import com.example.childgrowthsystem.demo.services.ConsultationService;
import com.example.childgrowthsystem.demo.services.impl.ConsultationServiceImpl;
import com.example.childgrowthsystem.entity.ChildProfiles;
import com.example.childgrowthsystem.entity.Consultations;
import com.example.childgrowthsystem.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BeApplicationTests {

	@Test
	void contextLoads() {
	}

    static class ConsultationServiceTest {

        private ConsultationRepository consultationRepository;
        private ConsultationService consultationService;

        @BeforeEach
        void setUp() {
            consultationRepository = Mockito.mock(ConsultationRepository.class);
            consultationService = new ConsultationServiceImpl(consultationRepository);
        }

        @Test
        @DisplayName("Test createConsultation returns saved consultation")
        void testCreateConsultation() {
            Consultations consultation = new Consultations();
            consultation.setMessage("Test message");
            consultation.setUser(new Users());
            consultation.setDoctor(new Users());
            consultation.setChild(new ChildProfiles());

            when(consultationRepository.save(any(Consultations.class))).thenReturn(consultation);

            Consultations saved = consultationService.createConsultation(consultation);

            assertThat(saved.getMessage()).isEqualTo("Test message");
            verify(consultationRepository, times(1)).save(consultation);
        }

        @Test
        @DisplayName("Test getConsultationById returns correct result")
        void testGetConsultationById() {
            Consultations consultation = new Consultations();
            consultation.setConsultationID(100);

            when(consultationRepository.findById(100)).thenReturn(Optional.of(consultation));

            Optional<Consultations> result = consultationService.getConsultationById(100);

            assertThat(result).isPresent();
            assertThat(result.get().getConsultationID()).isEqualTo(100);
        }

        @Test
        @DisplayName("Test updateConsultationStatus updates and saves consultation")
        void testUpdateConsultationStatus() {
            Consultations consultation = new Consultations();
            consultation.setConsultationID(5);
            consultation.setStatus(Consultations.Status.Pending);

            when(consultationRepository.findById(5)).thenReturn(Optional.of(consultation));
            when(consultationRepository.save(any(Consultations.class))).thenReturn(consultation);

            Consultations updated = consultationService.updateConsultationStatus(
                    5, Consultations.Status.Completed, "Tư vấn xong");

            assertThat(updated.getStatus()).isEqualTo(Consultations.Status.Completed);
            assertThat(updated.getFeedbackText()).isEqualTo("Tư vấn xong");
        }
    }
}
