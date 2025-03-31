package com.example.childgrowthsystem.demo.services;

import com.example.childgrowthsystem.entity.Memberships;
import java.util.Optional;

public interface MembershipService
{
    // Đăng ký thành viên
    Memberships createMembership(Memberships membership);

    // Lấy thông tin gói thành viên theo ID
    Optional<Memberships> getMembershipById(int membershipId);

    // Lấy thông tin gói thành viên theo UserID
    Optional<Memberships> getMembershipByUserId(int userId);

    // Cập nhật gói thành viên
    Memberships updateMembership(int membershipId, Memberships updatedMembership);

    // Hủy gói thành viên
    void deleteMembership(int membershipId);
}
