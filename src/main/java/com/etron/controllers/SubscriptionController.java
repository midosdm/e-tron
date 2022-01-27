package com.etron.controllers;

import com.etron.dto.SubscriptionDto;
import com.etron.models.Subscription;
import com.etron.services.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
    private final ModelMapper modelMapper;

    private final SubscriptionService subscriptionService;


    @Autowired
    public SubscriptionController(ModelMapper modelMapper, SubscriptionService subscriptionService){
        this.modelMapper = modelMapper;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions(){
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping(path = "{subscriptionId}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable("subscriptionId") Long subscriptionId){
        return subscriptionService.getById(subscriptionId);
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody Subscription subscription){
        return subscriptionService.createSubscription(subscription);
    }

    @PutMapping(path="{subscriptionId}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable("subscriptionId") Long subscriptionId, @Valid @RequestBody Subscription subscription){
        return subscriptionService.updateSubscription(subscriptionId, subscription);
    }

    @DeleteMapping(path="{subscriptionId")
    public ResponseEntity<HttpStatus> deleteSubscription(@PathVariable("subscriptionId") Long subscriptionId){
        return subscriptionService.deleteSubscription(subscriptionId);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllSubscriptions(){
        return subscriptionService.deleteAllSubscriptions();
    }


}
