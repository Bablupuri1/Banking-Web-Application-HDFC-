package com.hdfc.DTO;

public class CustomerResponseCredentialDTO {

    private String customerId;
    private String password;

    public CustomerResponseCredentialDTO() {
    }

    public CustomerResponseCredentialDTO(String customerId, String password) {
        this.customerId = customerId;
        this.password = password;
    }

    // Getters and setters

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
