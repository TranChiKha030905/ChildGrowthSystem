package com.example.childgrowthsystem.demo.services;

import com.example.childgrowthsystem.entity.ChildProfiles;
import java.util.List;
import java.util.Optional;

public interface ChildProfileService
{

    // Tạo hồ sơ trẻ em mới
    ChildProfiles createChildProfile(ChildProfiles childProfile);

    // Tìm hồ sơ trẻ em theo ID
    Optional<ChildProfiles> getChildProfileById(int childID);

    // Lấy danh sách trẻ của một người dùng
    List<ChildProfiles> getChildrenByUserId(int userID);

    // Xóa hồ sơ trẻ
    void deleteChildProfile(int childID);
}
