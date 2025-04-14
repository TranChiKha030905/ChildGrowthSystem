package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.NutritionRecord;
import com.childgrowth.tracking.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NutritionRecordRepository extends JpaRepository<NutritionRecord, Long> {
    List<NutritionRecord> findByChildIdOrderByMealDateDesc(Long childId);
    
    List<NutritionRecord> findByChildIdAndMealDateBetweenOrderByMealDateDesc(
        Long childId, LocalDateTime startDate, LocalDateTime endDate);
    
    List<NutritionRecord> findByChildIdAndMealTypeOrderByMealDateDesc(
        Long childId, NutritionRecord.MealType mealType);
    
    @Query("SELECT n FROM NutritionRecord n WHERE n.child.id = :childId AND n.mealDate >= :startDate ORDER BY n.mealDate DESC")
    List<NutritionRecord> findRecentRecords(@Param("childId") Long childId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT AVG(n.calories) FROM NutritionRecord n WHERE n.child.id = :childId AND n.mealDate BETWEEN :startDate AND :endDate")
    Double calculateAverageCalories(@Param("childId") Long childId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    List<NutritionRecord> findByChild(Child child);
    
    List<NutritionRecord> findByChildAndMealDateBetween(Child child, LocalDateTime startDate, LocalDateTime endDate);
    
    List<NutritionRecord> findByChildAndMealType(Child child, NutritionRecord.MealType mealType);
} 