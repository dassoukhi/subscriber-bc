package com.canalPlus.subscriberbc.service;

import com.canalPlus.subscriberbc.dto.SubscriberDto;
import com.canalPlus.subscriberbc.model.Subscriber;
import com.canalPlus.subscriberbc.repository.SubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SubscriberServiceTest {
    @Mock
    private SubscriberRepository subscriberRepository;

    @Spy
    private ModelMapper modelMapper= new ModelMapper();


    @InjectMocks
    private SubscriberService subscriberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Test
    void testCreateSubscriber() {
        SubscriberDto subscriberDto = new SubscriberDto();
        subscriberDto.setFirstName("Canal");
        subscriberDto.setLastName("Plus");
        subscriberDto.setMail("canalplus@example.com");

        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("Canal");
        subscriber.setLastName("Plus");
        subscriber.setMail("canalplus@example.com");

        when(modelMapper.map(subscriberDto, Subscriber.class)).thenReturn(subscriber);
        when(subscriberRepository.save(any(Subscriber.class))).thenReturn(subscriber);

        Subscriber createdSubscriber = subscriberService.createSubscriber(subscriberDto);

        assertNotNull(createdSubscriber);
        assertEquals(subscriber.getFirstName(), createdSubscriber.getFirstName());
        assertEquals(subscriber.getLastName(), createdSubscriber.getLastName());
        assertEquals(subscriber.getMail(), createdSubscriber.getMail());

        verify(subscriberRepository, times(1)).save(any(Subscriber.class));
    }

    @Test
    void testGetSubscribers() {
        when(subscriberRepository.findByIsActiv(true)).thenReturn(Collections.singletonList(new Subscriber()));

        List<SubscriberDto> subscribers = subscriberService.getSubscribers();

        assertNotNull(subscribers);
        assertFalse(subscribers.isEmpty());

        verify(subscriberRepository, times(1)).findByIsActiv(true);
        verify(modelMapper, times(1)).map(any(Subscriber.class), eq(SubscriberDto.class));
    }

}
