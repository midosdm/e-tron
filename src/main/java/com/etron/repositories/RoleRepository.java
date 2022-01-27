package com.etron.repositories;

<<<<<<< HEAD
import com.etron.models.Role;
import com.etron.models.enums.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByAppRole(AppRole appRole);
=======
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etron.models.Role;
import com.etron.models.enums.AppRole;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByAppRole(AppRole appRole);
>>>>>>> 1e171ea... admin
}
