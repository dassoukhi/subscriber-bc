package com.canalPlus.subscriberbc.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "Subscriber")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriberID;
    private String firstName;
    private String lastName;
    @Column(unique=true)

    @Email(message = "Invalid email address")
    private String mail;
    @Column(unique=true)
    private String phone;
    private boolean isActiv;

    @Schema(name = "subscriberID", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public Long getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(Long subscriberID) {
        this.subscriberID = subscriberID;
    }

    public Subscriber() {
        super();
    }

    public Subscriber(Long subscriberID, String firstName, String lastName, String mail, String phone, boolean isActiv) {
        this.subscriberID = subscriberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.isActiv = isActiv;
    }

    @Schema(name = "firstName", example = "Canal", requiredMode = Schema.RequiredMode.REQUIRED)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String lastName) {
        this.firstName = lastName;
    }

    @Schema(name = "lastName", example = "Plus", requiredMode = Schema.RequiredMode.REQUIRED)
    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Schema(name = "mail", example = "canalplus@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    @Schema(name = "phone", example = "0171353535", requiredMode = Schema.RequiredMode.REQUIRED)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActiv() {
        return isActiv;
    }

    public void setActiv(boolean activ) {
        isActiv = activ;
    }
}
