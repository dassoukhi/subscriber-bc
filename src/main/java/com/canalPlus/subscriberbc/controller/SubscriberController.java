package com.canalPlus.subscriberbc.controller;

import com.canalPlus.subscriberbc.dto.SubscriberDto;
import com.canalPlus.subscriberbc.model.Subscriber;
import com.canalPlus.subscriberbc.service.SubscriberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriberController {
    @Autowired
    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

        @GetMapping("/subscribers")
    public ResponseEntity<List<SubscriberDto>> getSubscribers(){
        List<SubscriberDto> subscribers = this.subscriberService.getSubscribers();
        return ResponseEntity.ok(subscribers);
    }

    @PostMapping("subscriber/create")
    public ResponseEntity<Subscriber> createSubscriber(@RequestBody SubscriberDto subscriberDto){
        if (this.subscriberService.mailChecker(subscriberDto.getMail())) throw new ResponseStatusException(HttpStatus.CONFLICT, "A subscriber already exists by the same mail!");
        if (this.subscriberService.phoneChecker(subscriberDto.getPhone())) throw new ResponseStatusException(HttpStatus.CONFLICT, "A subscriber already exists by the same phone number!");

        Subscriber subscriberCreated = this.subscriberService.createSubscriber(subscriberDto);
        return ResponseEntity.ok(subscriberCreated);
    }
    @GetMapping("subscriber/get/mail/{mail}")
    public ResponseEntity<SubscriberDto> getSubscriberByMail(@PathVariable("mail") String mail){
        SubscriberDto subscriberDto = this.subscriberService.getSubscriberByMail(mail);
        return ResponseEntity.ok(subscriberDto);
    }
    @GetMapping("subscriber/get/firstName/{firstName}")
    public ResponseEntity<SubscriberDto> getSubscriberByFirstName(@PathVariable("firstName") String firstName){
        SubscriberDto subscriberDto = this.subscriberService.getSubscriberByFirstName(firstName);
        return ResponseEntity.ok(subscriberDto);
    }
    @GetMapping("subscriber/get/lastName/{lastName}")
    public ResponseEntity<SubscriberDto> getSubscriberByLastName(@PathVariable("lastName") String lastName){
        SubscriberDto subscriberDto = this.subscriberService.getSubscriberByLastName(lastName);
        return ResponseEntity.ok(subscriberDto);
    }
    @GetMapping("subscriber/get/phone/{phone}")
    public ResponseEntity<SubscriberDto> getSubscriberByPhone(@PathVariable("phone") String phone){
        SubscriberDto subscriberDto = this.subscriberService.getSubscriberByPhone(phone);
        return ResponseEntity.ok(subscriberDto);
    }
    @PostMapping("/unsubscribe")
    public ResponseEntity<Subscriber> unsubscribe(@RequestParam String mail){
        if (!this.subscriberService.mailChecker(mail)) throw new ResponseStatusException(HttpStatus.CONFLICT, "This subscriber is not exists");
        Subscriber subscriber = this.subscriberService.unsubscribe(mail);
        return ResponseEntity.ok(subscriber);
    }
    @PutMapping("subscriber/update")
    public ResponseEntity<SubscriberDto> updateSubscribe(@RequestBody Subscriber subscriber){
        SubscriberDto subscriberDto = this.subscriberService.updateSubscriber(subscriber);
        return ResponseEntity.ok(subscriberDto);
    }
}
