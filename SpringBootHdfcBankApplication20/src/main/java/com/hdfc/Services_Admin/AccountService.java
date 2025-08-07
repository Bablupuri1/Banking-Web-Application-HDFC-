package com.hdfc.Services_Admin;

import org.springframework.http.ResponseEntity;

import com.hdfc.ApiResponse.ApiResponse;
import com.hdfc.DTO.CustomerAccountDTO;
import com.hdfc.DTO.CustomerResponseCredentialDTO;
import com.hdfc.DTO.DepositRequestDTO;
import com.hdfc.DTO.DepositResponseDTO;
import com.hdfc.DTO.WithdrawRequestDTO;
import com.hdfc.DTO.WithdrawResponseDTO;

public interface AccountService {

    /**
     * Creates a new account for the customer.
     *
     * @param requestDto the DTO containing customer and account creation data
     * @return ApiResponse containing status and created account/customer data
     */
	public ResponseEntity<ApiResponse<CustomerResponseCredentialDTO>> createAccount(CustomerAccountDTO requestcreateaccount);
    public ResponseEntity<ApiResponse<DepositResponseDTO>> depositToAccount(DepositRequestDTO request);
    
    
    //implement feature measn after some days 
	//public ResponseEntity<ApiResponse<TransferResponseDTO>> transferMoney(TransferRequestDTO request) ;


	public ResponseEntity<ApiResponse<WithdrawResponseDTO>> withdrawFromAccount(WithdrawRequestDTO request) ;

    



}
