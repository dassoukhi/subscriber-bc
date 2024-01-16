package com.canalPlus.subscriberbc.repository;

import com.canalPlus.subscriberbc.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {


    Optional<Subscriber> findOneBySubscriberID(Long subscriberID);
    Optional<Subscriber> findOneByMail(String mail);
    Optional<Subscriber> findOneByFirstName(String firstName);
    Optional<Subscriber> findOneByLastName(String lastName);
    Optional<Subscriber> findOneByPhone(String phone);
    List<Subscriber> findByIsActiv(boolean isActiv);


}
