package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String type;

    private String date;
    private String mode;
    private String address;
    private String time;
    private String link;
    private String activityTitle;
    private String activityDescription;

    @Column(name = "activity_url")
    private String activityUrl;

    @ManyToOne
    @JoinColumn(name = "edict_id")
    private Edict edict;
}
