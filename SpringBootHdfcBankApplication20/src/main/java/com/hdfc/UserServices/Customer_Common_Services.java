package com.hdfc.UserServices;

import org.springframework.http.ResponseEntity;

import com.hdfc.ApiResponse.ApiResponse;
import com.hdfc.DTO.TransferRequestDTO;
import com.hdfc.DTO.TransferResponseDTO;

public interface Customer_Common_Services {

	public ResponseEntity<ApiResponse<TransferResponseDTO>> transferMoney(TransferRequestDTO transferDTO);
}
