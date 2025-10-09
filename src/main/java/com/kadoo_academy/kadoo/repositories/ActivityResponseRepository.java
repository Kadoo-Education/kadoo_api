package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.models.ActivityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityResponseRepository extends JpaRepository<ActivityResponse, Long> {
    boolean existsByUser_IdAndStep_Id(Long userId, Long stepId);
}
