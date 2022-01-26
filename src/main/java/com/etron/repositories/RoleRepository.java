package com.etron.repositories;

import com.etron.models.Role;
import com.etron.models.enums.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByAppRole(AppRole appRole);
}
