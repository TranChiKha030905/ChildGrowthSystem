package com.example.childgrowthsystem.demo.services.impl;

import com.example.childgrowthsystem.entity.Users;
import com.example.childgrowthsystem.repositories.UserRepository;
import com.example.childgrowthsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users createUser(Users user)
    {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
        {
            throw new RuntimeException("Email đã tồn tại!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<Users> getUserById(int userId)
    {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<Users> getUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Users> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int userId)
    {
        userRepository.deleteById(userId);
    }
}
