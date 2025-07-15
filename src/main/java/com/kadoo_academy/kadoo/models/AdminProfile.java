package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "admin_profile")
public class AdminProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId", unique = true)
    private User user;
}
