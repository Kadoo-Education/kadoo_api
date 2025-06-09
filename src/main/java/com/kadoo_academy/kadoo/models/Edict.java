package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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

        @Column(name = "description", nullable = false, length = 600)
        private String description;

        @Column(name = "link",nullable = false)
        private String linkDoc;

        @CreationTimestamp
        @Column(name = "created_at")
        private Date createdAt;

        @UpdateTimestamp
        @Column(name = "update_at")
        private Date updateAt;

        @Column(name = "active")
        private boolean active = true;

        @Column(name = "end_date")
        private Date endDate;

        @Column(name = "start_date")
        private Date startDate;
}
