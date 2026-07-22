package com.namay.loan.domain.model;

import com.namay.loan.domain.model.constant.ClientStatus;

public class Client {

    private Long id;
    private String document;
    private String fullName;
    private String phoneNumber;
    private String email;
    private ClientStatus status;

    public Client() {
    }

    public Client(Long id, String document, String fullName, String phoneNumber, String email, ClientStatus status) {
        this.id = id;
        this.document = document;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }
}
