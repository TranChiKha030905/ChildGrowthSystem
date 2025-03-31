package com.example.childgrowthsystem.demo.services;

import com.example.childgrowthsystem.entity.Alerts;
import java.util.List;
import java.util.Optional;

public interface AlertService
{

    // Tạo cảnh báo mới
    Alerts createAlert(Alerts alert);

    // Lấy danh sách tất cả cảnh báo
    List<Alerts> getAllAlerts();

    // Lấy cảnh báo theo ID
    Optional<Alerts> getAlertById(int alertId);

    // Lấy cảnh báo theo ChildID
    List<Alerts> getAlertsByChildId(int childID);

    // Xóa cảnh báo theo ID
    void deleteAlert(int alertId);
}
