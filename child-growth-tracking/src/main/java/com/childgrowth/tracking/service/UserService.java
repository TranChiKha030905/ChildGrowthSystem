package com.childgrowth.tracking.service;
import org.springframework.security.core.userdetails.User;
import com.childgrowth.tracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveUser(com.childgrowth.tracking.model.User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("MEMBER");
        userRepo.save(user);
    }

    public com.childgrowth.tracking.model.User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.childgrowth.tracking.model.User appUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserBuilder builder = User.withUsername(appUser.getUsername());
        builder.password(appUser.getPassword());
        builder.roles(appUser.getRole()); // MEMBER, ADMIN, etc.
        return builder.build();
    }
}
