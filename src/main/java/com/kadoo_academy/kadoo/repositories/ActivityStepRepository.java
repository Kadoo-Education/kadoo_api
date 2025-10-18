package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.models.ActivityStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityStepRepository extends JpaRepository<ActivityStep, Long> {}
