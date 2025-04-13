
package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.model.DoctorApproval;
import com.childgrowth.tracking.repository.UserRepository;
import com.childgrowth.tracking.repository.DoctorApprovalRepository;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DoctorApprovalRepository doctorApprovalRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
    }

    @Override
    public void requestDoctorApproval(User user, DoctorApproval approval) {
        approval.setUser(user);
        approval.setStatus("PENDING");
        approval.setRequestedAt(LocalDateTime.now());
        doctorApprovalRepository.save(approval);
    }

    @Override
    public void approveDoctor(Long approvalId) {
        DoctorApproval approval = doctorApprovalRepository.findById(approvalId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu duyệt"));
        User user = approval.getUser();
        user.setRole("DOCTOR");
        userRepository.save(user);
        doctorApprovalRepository.delete(approval);
    }

    @Override
    public void rejectDoctor(Long approvalId, String reason) {
        DoctorApproval approval = doctorApprovalRepository.findById(approvalId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu duyệt"));
        doctorApprovalRepository.delete(approval);
    }

    @Override
    public List<User> getAllDoctors() {
        return userRepository.findByRole("DOCTOR");
    }

    @Override
    public List<User> getAllMembers() {
        return userRepository.findByRole("MEMBER");
    }
}
