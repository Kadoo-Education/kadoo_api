package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.dto.response.GetAllStepDTO;
import com.kadoo_academy.kadoo.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
    List<Step> findByEdictId(Long edictId);
}
