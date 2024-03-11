package com.scott.repositories;

import com.scott.models.SNS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SNSRepository extends JpaRepository<SNS, Long> {
    //Page<SNS> findAll(Pageable pageable);
}
