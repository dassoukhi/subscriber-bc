package com.canalPlus.subscriberbc.controller;
import com.canalPlus.subscriberbc.dto.SubscriberDto;
import com.canalPlus.subscriberbc.model.Subscriber;
import com.canalPlus.subscriberbc.service.SubscriberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubscriberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SubscriberService subscriberService;

    @InjectMocks
    private SubscriberController subscriberController;

    @Test
    public void test_GetSubscriberByMail_200() {
        //GIVEN
        SubscriberDto subscriberDto = new SubscriberDto("Canal",
                "Plus",
               "canalplus@gmail.com",
                 "0171353535");
        //WHEN
        when(subscriberService.getSubscriberByMail(anyString())).thenReturn(subscriberDto);
        //THEN
        given().standaloneSetup(new SubscriberController(subscriberService))
                .pathParam("mail","canalplus@gmail.com")
                .when()
                .get("api/subscriber/get/mail/{mail}")
                .then().status(HttpStatus.OK)
                .body("mail", equalTo("canalplus@gmail.com"));

    }

    @Test
    public void test_GetTurnover_404() {
        //GIVEN
        //WHEN
        when(subscriberService.getSubscriberByMail(anyString())).thenReturn(null);
        //THEN
        given().standaloneSetup(new SubscriberController(subscriberService))
                .pathParam("mail","canalplus@gmail.com")
                .when()
                .get("api/subscriber/get/mail/{mail}")
                .then().status(HttpStatus.NOT_FOUND);

    }
}
