package com.scott.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scott.models.NationalTeam;

public interface NationalTeamRepository extends JpaRepository<NationalTeam, Long> {
    Page<NationalTeam> findAll(Pageable pageable);
}
