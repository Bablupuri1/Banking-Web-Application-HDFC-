package com.hdfc.Admin.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.ApiResponse.ApiResponse;
import com.hdfc.DTO.CustomerAccountDTO;
import com.hdfc.DTO.CustomerResponseCredentialDTO;
import com.hdfc.DTO.DepositRequestDTO;
import com.hdfc.DTO.DepositResponseDTO;
import com.hdfc.DTO.WithdrawRequestDTO;
import com.hdfc.DTO.WithdrawResponseDTO;
import com.hdfc.Services_Admin.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/create-account")
	public ResponseEntity<ApiResponse<CustomerResponseCredentialDTO>> createAccount(
			@RequestBody CustomerAccountDTO requestDto) {
		return adminService.createAccount(requestDto);
	}

	@PostMapping("/deposit")
	public ResponseEntity<ApiResponse<DepositResponseDTO>> deposit(@RequestBody DepositRequestDTO request) {
		System.out.println("AdminController.deposit()");
		return adminService.depositToAccount(request);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<ApiResponse<WithdrawResponseDTO>> withdrawFromAccount(WithdrawRequestDTO request) {

		ResponseEntity<ApiResponse<WithdrawResponseDTO>> withdrawFromAccount = adminService
				.withdrawFromAccount(request);

		return withdrawFromAccount;
	}

}
