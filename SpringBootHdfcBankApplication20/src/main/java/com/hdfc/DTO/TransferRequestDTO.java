package com.hdfc.DTO;
// TransferRequestDTO.java
public class TransferRequestDTO 
{
    private String fromAccount;
    private String toAccount;
    private double amounts;
    private String remarks; // optional
    
    
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public double getAmount() {
		return amounts;
	}
	public void setAmount(double amount) {
		this.amounts = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
    
}
