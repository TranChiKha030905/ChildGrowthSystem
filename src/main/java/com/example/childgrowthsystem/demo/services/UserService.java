package com.example.childgrowthsystem.demo.services;

import com.example.childgrowthsystem.entity.Users;

import java.util.Optional;
import java.util.List;

public interface UserService
{

    // Tạo người dùng mới
    Users createUser(Users user);

    // Tìm người dùng theo ID
    Optional<Users> getUserById(int userId);

    // Tìm người dùng theo email
    Optional<Users> getUserByEmail(String email);

    // Tìm tất cà người dùng
    List<Users> getAllUsers();

    // Xóa người dùng theo ID
    void deleteUser(int userId);
}
