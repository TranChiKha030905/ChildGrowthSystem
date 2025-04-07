package com.example.demo.serviceTest;

import com.example.childgrowthsystem.demo.repositories.AlertRepository;
import com.example.childgrowthsystem.demo.services.impl.AlertsServiceImpl;
import com.example.childgrowthsystem.entity.Alerts;
import com.example.childgrowthsystem.demo.services.AlertService;
import com.example.childgrowthsystem.entity.ChildProfiles;
import com.example.childgrowthsystem.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AlertServiceImplTest {

    private AlertRepository alertRepository;
    private AlertService alertService;

    @BeforeEach
    void setup() {
        alertRepository = Mockito.mock(AlertRepository.class);
        alertService = new AlertsServiceImpl(alertRepository);
    }

    @Test
    @DisplayName("Test tạo mới cảnh báo")
    void testCreateAlert() {
        ChildProfiles child = new ChildProfiles();
        child.setChildId(1);
        child.setChildName("Bé A");
        child.setDateOfBirth(LocalDate.of(2020, 1, 1));
        child.setGender(ChildProfiles.Gender.Female);
        child.setUser(new Users());

        Alerts alert = new Alerts(0, child, "Allergy", "Phản ứng dị ứng", LocalDateTime.now());

        when(alertRepository.save(alert)).thenReturn(alert);

        Alerts result = alertService.createAlert(alert);
        assertThat(result.getAlertType()).isEqualTo("Allergy");
    }

    @Test
    @DisplayName("Test lấy tất cả cảnh báo")
    void testGetAllAlerts() {
        when(alertRepository.findAll()).thenReturn(List.of(new Alerts()));
        assertThat(alertService.getAllAlerts()).hasSize(1);
    }

    @Test
    @DisplayName("Test lấy cảnh báo theo ID")
    void testGetAlertById() {
        Alerts alert = new Alerts();
        alert.setAlertID(10);
        when(alertRepository.findById(10)).thenReturn(Optional.of(alert));

        Optional<Alerts> result = alertService.getAlertById(10);
        assertThat(result).isPresent();
        assertThat(result.get().getAlertID()).isEqualTo(10);
    }

    @Test
    @DisplayName("Test xóa cảnh báo theo ID")
    void testDeleteAlert() {
        alertService.deleteAlert(5);
        verify(alertRepository, times(1)).deleteById(5);
    }
}
