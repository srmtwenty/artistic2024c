package com.scott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scott.models.Occupation;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {
}
