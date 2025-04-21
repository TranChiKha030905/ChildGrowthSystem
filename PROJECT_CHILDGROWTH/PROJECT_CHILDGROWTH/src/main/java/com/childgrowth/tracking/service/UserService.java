package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.model.DoctorApproval;
import java.util.List;

public interface UserService {

    void registerUser(User user); // Đăng ký người dùng

    void requestDoctorApproval(User user, DoctorApproval approval);

    void approveDoctor(Long approvalId);

    void rejectDoctor(Long approvalId, String reason);

    User getUserByUsername(String username); // Lấy người dùng qua username

    List<User> getAllDoctors();

    List<User> getAllMembers();

    List<User> getAllUsers();

    void deleteUser(Long id);

    void saveDoctor(User doctor);

    User getUserById(Long id);

    void saveMember(User member);

    User updateUser(Long id, User updatedMember);

    String purchasePlan(User user, Long planId);

    String encodePassword(String password);

    boolean matchesPassword(String currentPassword, String password);
}
