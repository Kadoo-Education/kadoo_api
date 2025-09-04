package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
    @Table(name = "activity_step")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ActivityStep {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "due_date", nullable = false)
        private LocalDateTime dueDate;

        @Column(name = "pdf", nullable = false)
        private String file;

        @OneToOne
        @JoinColumn(name = "step_id", nullable = false, unique = true)
        private Step step;
    }
