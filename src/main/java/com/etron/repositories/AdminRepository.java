package com.etron.repositories;

import com.etron.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.password = :password WHERE a.id = :id")
    void updatePassword(@Param("id") Long adminId, @Param("password") String password);

}
