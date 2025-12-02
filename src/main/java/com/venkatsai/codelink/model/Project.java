package com.venkatsai.codelink.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="Projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    @ElementCollection
    private List<String> techStack =  new ArrayList<>();
    private String duration;
    private String status;
    @ElementCollection
    private List<String> screenShots = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ElementCollection
    private List<String> urlLinks =  new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
