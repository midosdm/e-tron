package com.etron.controllers;

import com.etron.dto.SubscriptionDto;
import com.etron.models.Subscription;
import com.etron.services.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<SubscriptionDto> getSubscriptions(){
        var subscriptions = subscriptionService.getAllSubscriptions();
        return subscriptions.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private SubscriptionDto convertToDto(Subscription subscription){
        return modelMapper.map(subscription, SubscriptionDto.class);
    }
    private Subscription convertToEntity(SubscriptionDto productDto) {
        return modelMapper.map(productDto, Subscription.class);
    }
}
