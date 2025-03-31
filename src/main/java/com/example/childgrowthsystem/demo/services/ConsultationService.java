package com.example.childgrowthsystem.demo.services;

import com.example.childgrowthsystem.entity.Consultations;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    // Tạo một tư vấn mới
    Consultations createConsultation(Consultations consultation);

    // Lấy danh sách tất cả tư vấn
    List<Consultations> getAllConsultations();

    // Lấy tư vấn theo ID
    Optional<Consultations> getConsultationById(int consultationId);

    // Lấy tư vấn theo User ID (người yêu cầu)
    List<Consultations> getConsultationsByUserId(int userId);

    // Lấy tư vấn theo Doctor ID (bác sĩ tư vấn)
    List<Consultations> getConsultationsByDoctorId(int doctorId);

    // Lấy tư vấn theo Child ID (trẻ em)
    List<Consultations> getConsultationsByChildId(int childId);

    // Cập nhật trạng thái tư vấn
    Consultations updateConsultationStatus(int consultationId, Consultations.Status status, String feedbackText);
}
