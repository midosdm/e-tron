package com.etron.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etron.models.Role;
import com.etron.models.enums.AppRole;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByAppRole(AppRole appRole);

}
