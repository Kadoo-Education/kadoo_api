package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USER_TB")
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "EMAIL", length = 50)
    private String email;

}
