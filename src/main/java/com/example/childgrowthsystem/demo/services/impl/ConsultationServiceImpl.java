package com.example.childgrowthsystem.demo.services.impl;

import com.example.childgrowthsystem.demo.repositories.ConsultationRepository;
import com.example.childgrowthsystem.demo.services.ConsultationService;
import com.example.childgrowthsystem.entity.Consultations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService
{

    private final ConsultationRepository consultationRepository;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository)
    {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Consultations createConsultation(Consultations consultation)
    {
        consultation.setRequestDate(LocalDateTime.now()); // Gán thời gian hiện tại nếu chưa có
        consultation.setStatus(Consultations.Status.Pending); // Mặc định trạng thái là "Pending"
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultations> getAllConsultations()
    {
        return consultationRepository.findAll();
    }

    @Override
    public Optional<Consultations> getConsultationById(int consultationId)
    {
        return consultationRepository.findById(consultationId);
    }

    @Override
    public List<Consultations> getConsultationsByUserId(int userId)
    {
        return consultationRepository.findByUser_UserID(userId);
    }

    @Override
    public List<Consultations> getConsultationsByDoctorId(int doctorId)
    {
        return consultationRepository.findByDoctor_UserID(doctorId);
    }

    @Override
    public List<Consultations> getConsultationsByChildId(int childId)
    {
        return consultationRepository.findByChild_ChildID(childId);
    }

    @Override
    public Consultations updateConsultationStatus(int consultationId, Consultations.Status status, String feedbackText)
    {
        Optional<Consultations> optionalConsultation = consultationRepository.findById(consultationId);
        if (optionalConsultation.isPresent())
        {
            Consultations consultation = optionalConsultation.get();
            consultation.setStatus(status);
            consultation.setFeedbackText(feedbackText);
            consultation.setFeedbackCreatedAt(LocalDateTime.now()); // Gán thời gian phản hồi
            return consultationRepository.save(consultation);
        }
        return null;
    }
}
