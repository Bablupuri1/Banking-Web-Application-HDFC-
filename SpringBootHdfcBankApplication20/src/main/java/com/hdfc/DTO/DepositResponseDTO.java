package com.hdfc.DTO;

public class DepositResponseDTO {
    private String accountNumber;
    private String customerName;
    private double newBalance;
    private String message;

    public DepositResponseDTO() {}

    public DepositResponseDTO(String accountNumber, String customerName, double newBalance, String message) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.newBalance = newBalance;
        this.message = message;
    }

    // Getters and Setters

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
