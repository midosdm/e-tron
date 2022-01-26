package com.etron.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etron.models.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

	List<Subscriber> findByFirstNameContaining(String nom);
}
