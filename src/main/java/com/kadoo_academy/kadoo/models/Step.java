package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "step")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private String time;

    @Column(name = "status")
    private String status = "Ativo";

    @OneToOne(mappedBy = "step", cascade = CascadeType.ALL)
    private Event event;

    @OneToOne(mappedBy = "step", cascade = CascadeType.ALL, orphanRemoval = true)
    private ActivityStep activity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "edict_id", nullable = false)
    private Edict edict;
}
