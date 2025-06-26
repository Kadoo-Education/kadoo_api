package com.kadoo_academy.kadoo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileStudent {

    private Long id;
    private String birthDate;
    private String cpf;

    @OneToOne(mappedBy = "student")
    @JoinColumn()
    private User user;

}
