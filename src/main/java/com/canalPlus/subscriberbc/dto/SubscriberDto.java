package com.canalPlus.subscriberbc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class SubscriberDto {

    @NotNull
    @Size(min = 3, message = "firstName doit avoir au moins 4 caractères")
    private String firstName;
    @NotNull
    @Size(min = 3, message = "lastName doit avoir au moins 4 caractères")
    private String lastName;
    @NotNull
    @Email(message = "Invalid email address")
    private String mail;
    @NotNull
    private String phone;

    public SubscriberDto() {
        super();
    }

    public SubscriberDto(String firstName, String lastName, String mail, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
    }
    @Schema(name = "firstName", example = "Canal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public String getFirstName() {
        return firstName;
    }
    @Schema(name = "lastName", example = "Plus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public String getLastName() {
        return lastName;
    }
    @Schema(name = "mail", example = "canalplus@gmail.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public String getMail() {
        return mail;
    }
    @Schema(name = "phone", example = "0171353535", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public String getPhone() {
        return phone;
    }




    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriberDto that = (SubscriberDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(mail, that.mail) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, mail, phone);
    }

    @Override
    public String toString() {
        return "SubscriberDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
