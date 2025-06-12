package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.models.Edict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdictRepository extends JpaRepository<Edict, Long> {
}
