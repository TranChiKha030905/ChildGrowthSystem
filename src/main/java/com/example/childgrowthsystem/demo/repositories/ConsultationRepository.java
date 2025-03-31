package com.example.childgrowthsystem.demo.repositories;

import com.example.childgrowthsystem.entity.Consultations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultations, Integer>
{
    // Tìm các tư vấn theo ID người dùng
    List<Consultations> findByUser_UserID(int userId);

    // Tìm các tư vấn theo ID bác sĩ
    List<Consultations> findByDoctor_UserID(int doctorId);

    // Tìm các tư vấn theo ID trẻ
    List<Consultations> findByChild_ChildID(int childId);
}
