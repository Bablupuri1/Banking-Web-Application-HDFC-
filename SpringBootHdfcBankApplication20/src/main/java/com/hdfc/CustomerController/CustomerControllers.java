package com.hdfc.CustomerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hdfc.ApiResponse.ApiResponse;
import com.hdfc.DTO.TransferRequestDTO;
import com.hdfc.DTO.TransferResponseDTO;
import com.hdfc.UserServices.CustomerServiceImpl;

// This is a REST controller, so use @RestController instead of @Controller
@RestController
@RequestMapping("/Customer_Api")
public class CustomerControllers {

    // Injecting CustomerServiceImpl to handle business logic
    @Autowired
    private CustomerServiceImpl customerservice;

    // Endpoint to handle transfer money requests (HTTP POST)
    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<TransferResponseDTO>> transferMoney(@RequestBody TransferRequestDTO transferDTO) {
        
        // Delegating the transferMoney logic to service layer
        ResponseEntity<ApiResponse<TransferResponseDTO>> response = customerservice.transferMoney(transferDTO);

        // Returning the response received from service
        return response;
    }
}
