package com.etron.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etron.models.Subscriber;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

	List<Subscriber> findByFirstNameContaining(String nom);

	boolean existsByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE Admin a SET a.password = :password WHERE a.id = :id")
	void updatePassword(@Param("id") Long adminId, @Param("password") String password);

}
