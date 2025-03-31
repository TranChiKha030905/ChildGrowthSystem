package com.example.childgrowthsystem.demo.services.impl;

import com.example.childgrowthsystem.demo.repositories.MembershipRepository;
import com.example.childgrowthsystem.demo.repositories.UserRepository;
import com.example.childgrowthsystem.demo.services.MembershipService;
import com.example.childgrowthsystem.entity.Memberships;
import com.example.childgrowthsystem.entity.Users;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService
{

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;

    public MembershipServiceImpl(MembershipRepository membershipRepository, UserRepository userRepository)
    {
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Memberships createMembership(Memberships membership)
    {
        return membershipRepository.save(membership);
    }

    @Override
    public Optional<Memberships> getMembershipById(int membershipId)
    {
        return membershipRepository.findById(membershipId);
    }

    @Override
    public Optional<Memberships> getMembershipByUserId(int userId)
    {
        Optional<Users> user = userRepository.findById(userId);
        return user.flatMap(membershipRepository::findByUser);
    }

    @Override
    public Memberships updateMembership(int membershipId, Memberships updatedMembership)
    {
        return membershipRepository.findById(membershipId).map(existingMembership ->
        {
            existingMembership.setMembershipType(updatedMembership.getMembershipType());
            existingMembership.setPrice(updatedMembership.getPrice());
            existingMembership.setStartDate(updatedMembership.getStartDate());
            existingMembership.setEndDate(updatedMembership.getEndDate());
            existingMembership.setPaymentMethod(updatedMembership.getPaymentMethod());
            existingMembership.setTransactionStatus(updatedMembership.getTransactionStatus());
            return membershipRepository.save(existingMembership);
        }).orElseThrow(() -> new RuntimeException("Membership not found"));
    }

    @Override
    public void deleteMembership(int membershipId)
    {
        membershipRepository.deleteById(membershipId);
    }
}
