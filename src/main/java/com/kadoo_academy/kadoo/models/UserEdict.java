package com.kadoo_academy.kadoo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_edict")
public class UserEdict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User userSubscribe;

    @Column(name = "nameSubscribe")
    private String nameSubscribe;

    @ManyToOne
    @JoinColumn(name = "idEdict", nullable = false)
    private Edict edict;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = false)
    private Date updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active = true;

}
