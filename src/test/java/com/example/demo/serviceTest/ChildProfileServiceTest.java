package com.example.demo.serviceTest;

import com.example.childgrowthsystem.entity.ChildProfiles;
import com.example.childgrowthsystem.entity.Users;
import com.example.childgrowthsystem.demo.services.impl.ChildProfileServiceImpl;
import com.example.childgrowthsystem.demo.repositories.ChildProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ChildProfileServiceTest {

    @Mock
    private ChildProfileRepository childProfileRepository;

    @InjectMocks
    private ChildProfileServiceImpl childProfileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateChildProfile() {
        Users user = new Users();
        user.setUserID(1);

        ChildProfiles child = new ChildProfiles(0, "Tí", LocalDate.of(2019, 7, 20),
                ChildProfiles.Gender.Male, 105.0, 18.0, 16.3, user);

        when(childProfileRepository.save(any(ChildProfiles.class))).thenReturn(child);

        ChildProfiles saved = childProfileService.createChildProfile(child);
        assertThat(saved.getChildName()).isEqualTo("Tí");
        verify(childProfileRepository, times(1)).save(child);
    }

    @Test
    public void testGetChildProfileById() {
        Users user = new Users();
        user.setUserID(1);

        ChildProfiles child = new ChildProfiles(1, "Na", LocalDate.of(2020, 3, 15),
                ChildProfiles.Gender.Female, 98.0, 15.0, 15.6, user);

        when(childProfileRepository.findById(1)).thenReturn(Optional.of(child));

        Optional<ChildProfiles> result = childProfileService.getChildProfileById(1);
        assertThat(result).isPresent();
        assertThat(result.get().getChildName()).isEqualTo("Na");
    }

    @Test
    public void testDeleteChildProfile() {
        int childId = 1;
        doNothing().when(childProfileRepository).deleteById(childId);
        childProfileService.deleteChildProfile(childId);
        verify(childProfileRepository, times(1)).deleteById(childId);
    }
}
