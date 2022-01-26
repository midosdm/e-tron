package com.etron.repositories;

import com.etron.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription getBySubscriptionType(String type);
    boolean existsBySubscriptionType(String type);
}