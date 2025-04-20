package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.MembershipPlan;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface MembershipPlanService {
    List<MembershipPlan> getAllMembershipPlans();

    Optional<MembershipPlan> getMembershipPlanById(Long id);

    MembershipPlan saveMembershipPlan(@Valid MembershipPlan plan);

    MembershipPlan deleteMembershipPlan(Long id);

    MembershipPlan updateMembershipPlan(Long id, MembershipPlan planDetails);
}
