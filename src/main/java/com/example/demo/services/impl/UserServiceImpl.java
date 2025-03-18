package com.example.demo.services.impl;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
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
    public User createUser(User user) 
    {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() 
    {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) 
    {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) 
    {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) 
        {
            User updatedUser = existingUser.get();
            updatedUser.setEmail(user.getEmail());
            updatedUser.setFullName(user.getFullName());
            updatedUser.setPassword(user.getPassword());
            return userRepository.save(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) 
    {
        userRepository.deleteById(id);
    }
}
