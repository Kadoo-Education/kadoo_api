package com.kadoo_academy.kadoo.models;

import com.kadoo_academy.kadoo.models.enums.UserEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String birthDate;
    @Column(unique = true)
    private String cpf;
    @Column(name = "type")
    private UserEnum type = UserEnum.STUDENT;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId",unique = true)
    private User user;

}
