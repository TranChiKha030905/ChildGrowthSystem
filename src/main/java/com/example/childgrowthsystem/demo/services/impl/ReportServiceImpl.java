package com.example.childgrowthsystem.demo.services.impl;

import com.example.childgrowthsystem.demo.repositories.ReportRepository;
import com.example.childgrowthsystem.demo.repositories.UserRepository;
import com.example.childgrowthsystem.demo.services.ReportService;
import com.example.childgrowthsystem.entity.Reports;
import com.example.childgrowthsystem.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService
{

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public ReportServiceImpl(ReportRepository reportRepository, UserRepository userRepository)
    {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Reports createReport(Reports report)
    {
        return reportRepository.save(report);
    }

    @Override
    public Optional<Reports> getReportById(int reportId)
    {
        return reportRepository.findById(reportId);
    }

    @Override
    public List<Reports> getReportsByUserId(int userId)
    {
        Optional<Users> user = userRepository.findById(userId);
        return user.map(reportRepository::findByGeneratedBy).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Reports updateReport(int reportId, Reports updatedReport)
    {
        return reportRepository.findById(reportId).map(existingReport ->
        {
            existingReport.setReportName(updatedReport.getReportName());
            existingReport.setDescription(updatedReport.getDescription());
            existingReport.setGeneratedAt(updatedReport.getGeneratedAt());
            existingReport.setReportData(updatedReport.getReportData());
            return reportRepository.save(existingReport);
        }).orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Override
    public void deleteReport(int reportId)
    {
        reportRepository.deleteById(reportId);
    }
}
