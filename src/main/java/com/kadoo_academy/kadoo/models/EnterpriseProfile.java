package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enterprise_profile")
public class EnterpriseProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cnpj;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId", unique = true)
    private User user;
}
