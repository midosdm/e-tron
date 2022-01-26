package com.etron.services;

import com.etron.exceptions.TypeIsTakenException;
import com.etron.models.Contrat;
import com.etron.models.Subscription;
import com.etron.repositories.SubscriberRepository;
import com.etron.repositories.SubscriptionRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        try{
            List<Subscription> subscriptions = new ArrayList<Subscription>();
            subscriptionRepository.findAll().forEach(subscriptions::add);
            if(subscriptions.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(subscriptions, HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Subscription> getByType(String type){
        try{
            Subscription subscription = new Subscription();
            subscription = subscriptionRepository.getBySubscriptionType(type);
            if(subscription == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(subscription, HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Subscription> getById(Long id){
        try{
            Subscription subscription = new Subscription();
            subscription = subscriptionRepository.getById(id);
            if(subscription == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(subscription, HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Subscription> createSubscription(Subscription subscription){
        try{
            if(subscriptionRepository.existsBySubscriptionType(subscription.getSubscriptionType().toString())){
                throw new TypeIsTakenException(Subscription.class, subscription.getSubscriptionType().toString());
            }

            Subscription newSubscription = subscriptionRepository.save(new Subscription(subscription.getSubscriptionType(), subscription.getDuration(), subscription.getFraisDeBase(),
                    subscription.getChargeAc(), subscription.getChargeDc(), subscription.isChargeRapideActive(), subscription.getChargeRapide()));
            return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Subscription> updateSubscription(Long id, Subscription subscription){
        Optional<Subscription> subscriptionData = subscriptionRepository.findById(id);

        if(subscriptionData.isPresent()){
            Subscription newSubscription = subscriptionData.get();

            newSubscription.setSubscriptionType(subscription.getSubscriptionType());
            newSubscription.setSubscriberList(subscription.getSubscriberList());
            newSubscription.setDuration(subscription.getDuration());
            newSubscription.setFraisDeBase(subscription.getFraisDeBase());
            newSubscription.setChargeAc(subscription.getChargeAc());
            newSubscription.setChargeDc(subscription.getChargeDc());
            newSubscription.setChargeRapide(subscription.getChargeRapide());
            //newSubscription.isChargeRapideActive(subscription.isChargeRapideActive);
            return new ResponseEntity<>(subscriptionRepository.save(newSubscription), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteSubscription(Long id){
        try{
            subscriptionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllSubscriptions(){
        try{
            subscriptionRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
