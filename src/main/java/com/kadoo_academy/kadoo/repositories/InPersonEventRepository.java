package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.models.InPersonEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InPersonEventRepository extends JpaRepository<InPersonEvent, Long> {}

