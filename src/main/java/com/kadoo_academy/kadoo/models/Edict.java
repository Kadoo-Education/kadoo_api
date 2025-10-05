package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "edict")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edict {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id",nullable = false)
        private Long id;

        @Column(name = "title",nullable = false)
        private String title;

        @Column(name = "description", nullable = false, length = 2000)
        private String description;

        @Column(name = "organizer", nullable = false)
        private String organizer;

        @Column(name = "contact", nullable = false)
        private String contact;

        @Column(name = "start_date")
        private LocalDate startDate;

        @Column(name = "end_date")
        private LocalDate endDate;

        @Column(name = "pdf",nullable = false)
        private String pdf;

        @Column(name = "categories")
        private ArrayList<String> categories = new ArrayList<>();

        @Column(name = "status", nullable = false)
        private String status;

        @Column(name = "location", nullable = false)
        private String location;

        @CreationTimestamp
        @Column(name = "created_at", nullable = false, updatable = false)
        private LocalDate createdAt;

        @UpdateTimestamp
        @Column(name = "update_at")
        private LocalDateTime updateAt;

        @OneToMany(mappedBy = "edict", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Step> steps = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "userId")
        private User user;
}
