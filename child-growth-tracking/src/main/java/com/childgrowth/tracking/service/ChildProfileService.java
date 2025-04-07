package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.ChildProfile;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.ChildProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildProfileService {

    @Autowired
    private ChildProfileRepository childRepo;

    public void saveChild(ChildProfile child) {
        childRepo.save(child);
    }

    public List<ChildProfile> getChildrenByUser(User user) {
        return childRepo.findByParent(user);
    }
}
