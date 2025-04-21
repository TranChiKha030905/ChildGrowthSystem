package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdviceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String response;        //bác sĩ sẽ phản hồi vào đây
    private LocalDate createdAt;
    private boolean resolved;       //nếu bác sĩ phản hồi thì sẽ hiện true

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_profile_id")
    private ChildProfile child;
}
