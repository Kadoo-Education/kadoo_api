package com.kadoo_academy.kadoo.models;

import com.kadoo_academy.kadoo.models.enums.UserEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 50)
    private UserEnum type;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = false)
    private Date updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active = true;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Edict> edictList = new ArrayList<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private ProfileStudent student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private MentorProfile mentor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AdminProfile admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (admin != null) {
            return List.of(() -> "ROLE_ADMIN");
        }
        if (mentor != null) {
            return List.of(() -> "ROLE_MENTOR");
        }
        if (student != null) {
            return List.of(() -> "ROLE_STUDENT");
        }
        return List.of(() -> "ROLE_UNKNOWN");
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
