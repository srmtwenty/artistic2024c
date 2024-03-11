package com.scott.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.scott.models.Broadcast;

public interface BroadcastRepository extends JpaRepository<Broadcast, Long> {

}
