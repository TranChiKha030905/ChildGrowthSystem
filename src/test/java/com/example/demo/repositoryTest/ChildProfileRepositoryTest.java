package com.example.demo.repositoryTest;

import com.example.childgrowthsystem.demo.repositories.ChildProfileRepository;
import com.example.childgrowthsystem.entity.ChildProfiles;
import com.example.childgrowthsystem.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ChildProfileRepositoryTest {

    @Autowired
    private ChildProfileRepository childProfileRepository;

    @Test
    @DisplayName("Test findByUser_UserID returns children")
    public void testFindByUser_UserID() {
        Users user = new Users();
        user.setUserID(1); // Giả định userID này đã tồn tại

        ChildProfiles child = new ChildProfiles(0, "Bé Na", LocalDate.of(2020, 5, 15),
                ChildProfiles.Gender.Female, 100.0, 16.0, 16.0, user);
        childProfileRepository.save(child);

        List<ChildProfiles> result = childProfileRepository.findByUser_UserID(1);
        assertThat(result).isNotEmpty();
    }

    @Test
    @DisplayName("Test findByChildName returns children with given name")
    public void testFindByChildName() {
        Users user = new Users();
        user.setUserID(2);

        ChildProfiles child = new ChildProfiles(0, "Bin", LocalDate.of(2021, 1, 1),
                ChildProfiles.Gender.Male, 90.0, 14.0, 17.3, user);
        childProfileRepository.save(child);

        List<ChildProfiles> result = childProfileRepository.findByChildName("Bin");
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getChildName()).isEqualTo("Bin");
    }
}
