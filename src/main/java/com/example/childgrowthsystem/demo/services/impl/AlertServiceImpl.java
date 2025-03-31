package com.example.childgrowthsystem.demo.services.impl;

import com.example.childgrowthsystem.entity.Alerts;
import com.example.childgrowthsystem.demo.repository.AlertsRepository;
import com.example.childgrowthsystem.demo.services.AlertsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertsServiceImpl implements AlertsService
{

    private final AlertsRepository alertsRepository;

    public AlertsServiceImpl(AlertsRepository alertsRepository)
    {
        this.alertsRepository = alertsRepository;
    }

    @Override
    public Alerts createAlert(Alerts alert)
    {
        return alertsRepository.save(alert);
    }

    @Override
    public List<Alerts> getAllAlerts()
    {
        return alertsRepository.findAll();
    }

    @Override
    public Optional<Alerts> getAlertById(int alertId)
    {
        return alertsRepository.findById(alertId);
    }

    @Override
    public List<Alerts> getAlertsByChildId(int childID)
    {
        return alertsRepository.findByChild_ChildID(childID);
    }

    @Override
    public void deleteAlert(int alertId)
    {
        alertsRepository.deleteById(alertId);
    }
}
