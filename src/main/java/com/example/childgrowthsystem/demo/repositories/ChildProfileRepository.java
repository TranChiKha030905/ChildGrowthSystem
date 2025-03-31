package com.example.childgrowthsystem.demo.repositories;

import com.example.childgrowthsystem.entity.ChildProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildProfileRepository extends JpaRepository<ChildProfiles, Integer>
{

    // Tìm danh sách hồ sơ trẻ em theo User ID
    List<ChildProfiles> findByUser_UserID(int userID);

    // Tìm trẻ theo tên (có thể có nhiều trẻ trùng tên)
    List<ChildProfiles> findByChildName(String childName);
}
