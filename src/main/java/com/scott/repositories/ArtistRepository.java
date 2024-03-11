package com.scott.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scott.models.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Page<Artist> findAll(Pageable pageable);
}
