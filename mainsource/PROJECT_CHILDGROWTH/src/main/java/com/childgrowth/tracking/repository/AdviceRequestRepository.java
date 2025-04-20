package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.AdviceRequest;
import com.childgrowth.tracking.model.Child;
import com.childgrowth.tracking.model.ChildProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AdviceRequestRepository extends JpaRepository<AdviceRequest, Long> {
    List<AdviceRequest> findByResolved(boolean resolved);
    List<AdviceRequest> findByChild_User_Id(Long userId);

    // Hoặc nếu muốn theo ID
    List<AdviceRequest> findByChildId(Long childId);

    List<AdviceRequest> findByChild(ChildProfile profile);

    //tìm yêu cầu chưa được phản hồi
    List<AdviceRequest> findByResolvedFalse();
}
