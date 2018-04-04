package com.nataliawellness.nataliawellness.services;


import com.nataliawellness.nataliawellness.entities.Subscription;
import com.nataliawellness.nataliawellness.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public Subscription findByEmail(String email) {

        return subscriptionRepository.findByEmail(email);
    }

    public void save(Subscription subscription) {

        subscription.setCreatedAt(new Date());

        subscriptionRepository.save(subscription);
    }
}
