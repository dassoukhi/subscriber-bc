package com.canalPlus.subscriberbc.service;

import com.canalPlus.subscriberbc.dto.SubscriberDto;
import com.canalPlus.subscriberbc.model.Subscriber;
import com.canalPlus.subscriberbc.repository.SubscriberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SubscriberService {
    @Autowired
    private final SubscriberRepository subscriberRepository;
    @Autowired
    private ModelMapper modelMapper;

    public SubscriberService(SubscriberRepository subscriberRepository, ModelMapper modelMapper) {
        this.subscriberRepository = subscriberRepository;
        this.modelMapper = modelMapper;
    }
    public Subscriber createSubscriber(SubscriberDto subscriberDto) {

        Subscriber newSubscriber = this.modelMapper.map(subscriberDto, Subscriber.class);
        newSubscriber.setActiv(true);
        return subscriberRepository.save(newSubscriber);
    }
    public List<SubscriberDto> getSubscribers() {
        List<Subscriber> subscribers = subscriberRepository.findByIsActiv(true);
        return subscribers.stream().map(subscriber -> this.modelMapper.map(subscriber, SubscriberDto.class)).collect(Collectors.toList());
    }
    public SubscriberDto getSubscriberByMail(String mail) {
        Subscriber subscriber = subscriberRepository.findOneByMail(mail).get();
        return this.modelMapper.map(subscriber, SubscriberDto.class);
    }

    public SubscriberDto getSubscriberByFirstName(String firstName) {
        Subscriber subscriber = subscriberRepository.findOneByFirstName(firstName).get();
        System.out.println(subscriber);
        return this.modelMapper.map(subscriber, SubscriberDto.class);
    }
    public SubscriberDto getSubscriberByLastName(String lastName) {
        Subscriber subscriber = subscriberRepository.findOneByLastName(lastName).get();
        System.out.println(subscriber);
        return this.modelMapper.map(subscriber, SubscriberDto.class);
    }

    public SubscriberDto getSubscriberByPhone(String phone) {
        Subscriber subscriber = subscriberRepository.findOneByPhone(phone).get();
        System.out.println(subscriber);
        return this.modelMapper.map(subscriber, SubscriberDto.class);
    }

    public SubscriberDto getSubscriberById(Long subscriberID) {
        Subscriber subscriber = subscriberRepository.findOneBySubscriberID(subscriberID).get();
        return this.modelMapper.map(subscriber, SubscriberDto.class);
    }
    public boolean mailChecker(String mail) {
        Optional<Subscriber> subscriber = subscriberRepository.findOneByMail(mail);
        return subscriber.isPresent();
    }
    public boolean phoneChecker(String phone) {
        Optional<Subscriber> subscriber = subscriberRepository.findOneByPhone(phone);
        return subscriber.isPresent();
    }
    public Subscriber unsubscribe(String mail) {
        Subscriber subscriber = subscriberRepository.findOneByMail(mail).get();
        if (!subscriber.isActiv()) throw new ResponseStatusException(HttpStatus.CONFLICT, "This user is already unsubscribed");
        subscriber.setActiv(false);
        return subscriberRepository.save(subscriber);
    }
    public SubscriberDto updateSubscriber(Subscriber subscriber) {
        Subscriber updateSubscriber = subscriberRepository.findOneBySubscriberID(subscriber.getSubscriberID()).get();
        updateSubscriber.setFirstName(subscriber.getFirstName());
        updateSubscriber.setLastName(subscriber.getLastName());
        updateSubscriber.setPhone(subscriber.getPhone());
        updateSubscriber.setMail(subscriber.getMail());
        subscriberRepository.save(updateSubscriber);
        return this.modelMapper.map(updateSubscriber, SubscriberDto.class);
    }
}
