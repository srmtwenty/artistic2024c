package com.scott.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scott.models.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Page<Music> findAll(Pageable pageable);
}
