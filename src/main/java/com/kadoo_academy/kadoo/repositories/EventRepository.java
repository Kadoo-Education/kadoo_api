package com.kadoo_academy.kadoo.repositories;

import com.kadoo_academy.kadoo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {}
