package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {
    List<FAQ> findByCategory(String category);
    List<FAQ> findByIsActive(boolean isActive);
    List<FAQ> findByIsFeatured(boolean isFeatured);
    List<FAQ> findByLanguage(String language);
    List<FAQ> findByIsApproved(boolean isApproved);
    List<FAQ> findByDisplayOrderLessThanEqual(Integer maxOrder);
    List<FAQ> findByTagsContaining(String tag);
    List<FAQ> findByQuestionContainingIgnoreCase(String keyword);
} 