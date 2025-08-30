package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "online_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meeting_link")
    private String meetingLink;

    @OneToOne
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private Event event;
}
