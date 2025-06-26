package com.kadoo_academy.kadoo.models;

import com.kadoo_academy.kadoo.models.enums.UserEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile_mentor")

public class ProfileMentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String specialty;

    @Column(nullable = false, length = 100)
    private String bio;

    @Column(nullable = false, length = 50)
    private String linkedin;

    @Column(length = 150)
    private String profileImage;

    @Column(nullable = false, length = 50)
    private UserEnum type = UserEnum.MENTOR;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

}
