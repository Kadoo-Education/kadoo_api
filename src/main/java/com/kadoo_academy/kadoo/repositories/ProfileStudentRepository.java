package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.models.ProfileStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileStudentRepository extends JpaRepository<ProfileStudent,Long> {
}
