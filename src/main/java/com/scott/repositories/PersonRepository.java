package com.scott.repositories;


import com.scott.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByOrderByNameAsc();
    Page<Person> findAll(Pageable pageable);
    List<Person> findByNameContaining(String name);
}
