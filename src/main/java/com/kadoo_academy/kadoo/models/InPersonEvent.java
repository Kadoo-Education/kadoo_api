package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "in_person_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InPersonEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private Event event;
}
