package com.kadoo_academy.kadoo.repository;

import com.kadoo_academy.kadoo.models.Edict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EdictRepository extends JpaRepository<Edict, Long> {
}
