package com.scott.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scott.models.Nation;

import java.util.List;

public interface NationRepository extends JpaRepository<Nation, Long>{
    List<Nation> findAllByOrderByNameAsc();
    Page<Nation> findAll(Pageable pageable);
    //List<Nation> findByOrderByNameAsc();
}