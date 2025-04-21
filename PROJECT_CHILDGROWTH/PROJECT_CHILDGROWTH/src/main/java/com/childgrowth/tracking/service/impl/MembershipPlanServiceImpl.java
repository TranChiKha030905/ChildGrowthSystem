package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.MembershipPlan;
import com.childgrowth.tracking.repository.MembershipPlanRepository;
import com.childgrowth.tracking.service.MembershipPlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipPlanServiceImpl implements MembershipPlanService {

    private final MembershipPlanRepository membershipPlanRepository;

    public MembershipPlanServiceImpl(MembershipPlanRepository membershipPlanRepository) {
        this.membershipPlanRepository = membershipPlanRepository;
    }

    @Override
    public List<MembershipPlan> getAllMembershipPlans() {
        return membershipPlanRepository.findAll();
    }

    @Override
    public Optional<MembershipPlan> getMembershipPlanById(Long id) {
        return membershipPlanRepository.findById(id);
    }

    @Override
    public MembershipPlan saveMembershipPlan(MembershipPlan plan) {
        // Kiểm tra và xử lý logic nghiệp vụ nếu cần
        if (plan.getPrice() < 0) {
            throw new IllegalArgumentException("Giá gói không được âm");
        }
        if (plan.getMaxChildren() <= 0) {
            throw new IllegalArgumentException("Số trẻ tối đa phải lớn hơn 0");
        }

        return membershipPlanRepository.save(plan);
    }

    @Override
    public MembershipPlan deleteMembershipPlan(Long id) {
        // Kiểm tra tồn tại trước khi xóa
        if (!membershipPlanRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy gói thành viên với ID: " + id);
        }

        membershipPlanRepository.deleteById(id);
        return null;
    }

    @Override
    public MembershipPlan updateMembershipPlan(Long id, MembershipPlan planDetails) {
        return membershipPlanRepository.findById(id)
                .map(existingPlan -> {
                    existingPlan.setName(planDetails.getName());
                    existingPlan.setDescription(planDetails.getDescription());
                    existingPlan.setPrice(planDetails.getPrice());
                    existingPlan.setMaxChildren(planDetails.getMaxChildren());
                    existingPlan.setAllowDoctorConsult(planDetails.isAllowDoctorConsult());
                    return membershipPlanRepository.save(existingPlan);
                })
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy gói thành viên với ID: " + id));
    }
}