package com.example.childgrowthsystem.demo.services;

import com.example.childgrowthsystem.entity.Reports;
import java.util.List;
import java.util.Optional;

public interface ReportService
{
    // Tạo report mới
    Reports createReport(Reports report);

    // Lấy thông tin report theo ID
    Optional<Reports> getReportById(int reportId);

    // Lấy tất cả report của một User
    List<Reports> getReportsByUserId(int userId);

    // Cập nhật report
    Reports updateReport(int reportId, Reports updatedReport);

    // Xóa report
    void deleteReport(int reportId);
}
