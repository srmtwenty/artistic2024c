package com.scott.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scott.models.ERole;
import com.scott.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
