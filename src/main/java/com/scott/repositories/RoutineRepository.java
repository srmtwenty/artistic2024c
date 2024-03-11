package com.scott.repositories;

import com.scott.models.Routine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    Page<Routine> findAll(Pageable pageable);
}
