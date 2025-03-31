package com.example.childgrowthsystem.demo.repositories;

import com.example.childgrowthsystem.entity.Reports;
import com.example.childgrowthsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Reports, Integer>
{
    // Tìm tất cả báo cáo do một người dùng tạo
    List<Reports> findByGeneratedBy(Users user);
}
