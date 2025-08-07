package com.hdfc.DTO;
public class WithdrawResponseDTO {
    private String accountNumber;
    private double withdrawnAmount;
    private double availableBalance;
    private String status;
    private String transactionRef;
    
    
    
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getWithdrawnAmount() {
		return withdrawnAmount;
	}
	public void setWithdrawnAmount(double withdrawnAmount) {
		this.withdrawnAmount = withdrawnAmount;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransactionRef() {
		return transactionRef;
	}
	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}
    
    
    
    
}
