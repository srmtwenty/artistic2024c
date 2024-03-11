package com.scott.repositories;

import com.scott.models.Soundtrack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoundtrackRepository extends JpaRepository<Soundtrack, Long> {
    Page<Soundtrack> findAll(Pageable pageable);
}
