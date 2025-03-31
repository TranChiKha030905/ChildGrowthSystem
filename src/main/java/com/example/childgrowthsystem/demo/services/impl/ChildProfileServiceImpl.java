package com.example.childgrowthsystem.demo.services.impl;

import com.example.childgrowthsystem.entity.ChildProfiles;
import com.example.childgrowthsystem.service.ChildProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildProfileServiceImpl implements ChildProfileService
{

    @Autowired
    private com.example.childgrowthsystem.repository.ChildProfileRepository childProfilesRepository;

    @Override
    public ChildProfiles createChildProfile(ChildProfiles childProfile)
    {
        return childProfilesRepository.save(childProfile);
    }

    @Override
    public Optional<ChildProfiles> getChildProfileById(int childID)
    {
        return childProfilesRepository.findById(childID);
    }

    @Override
    public List<ChildProfiles> getChildrenByUserId(int userID)
    {
        return childProfilesRepository.findByUser_UserID(userID);
    }

    @Override
    public void deleteChildProfile(int childID)
    {
        childProfilesRepository.deleteById(childID);
    }
}
