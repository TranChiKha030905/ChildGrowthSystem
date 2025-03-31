package com.example.childgrowthsystem.demo.repositories;

import com.example.childgrowthsystem.entity.Alerts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alerts, Integer>
{

    // Lấy danh sách cảnh báo theo ChildID
    List<Alerts> findByChild_ChildID(int childID);
}
