package com.canalPlus.subscriberbc.controller;
import com.canalPlus.subscriberbc.dto.SubscriberDto;
import com.canalPlus.subscriberbc.model.Subscriber;
import com.canalPlus.subscriberbc.service.SubscriberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(SubscriberController.class)
public class SubscriberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SubscriberService subscriberService;

    @InjectMocks
    private SubscriberController subscriberController;

    @Test
    void testGetSubscribers() throws Exception {
        SubscriberDto subscriberDto = new SubscriberDto();
        when(subscriberService.getSubscribers()).thenReturn(Collections.singletonList(subscriberDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/subscribers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value(subscriberDto.getFirstName()));
    }

    @Test
    void testCreateSubscriber() throws Exception {
        SubscriberDto subscriberDto = new SubscriberDto();
        subscriberDto.setFirstName("John");

        when(subscriberService.createSubscriber(any(SubscriberDto.class))).thenReturn(new Subscriber());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/subscriber/create")
                        .content(asJsonString(subscriberDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
