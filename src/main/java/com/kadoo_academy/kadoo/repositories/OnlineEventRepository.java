package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.models.OnlineEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineEventRepository extends JpaRepository<OnlineEvent, Long> {}

