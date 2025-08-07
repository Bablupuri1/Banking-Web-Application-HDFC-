// Package declaration
package com.hdfc.Services_Admin;

// Import statements
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hdfc.ApiResponse.ApiResponse;
import com.hdfc.DTO.CustomerAccountDTO;
import com.hdfc.DTO.CustomerResponseCredentialDTO;
import com.hdfc.DTO.DepositRequestDTO;
import com.hdfc.DTO.DepositResponseDTO;
import com.hdfc.DTO.WithdrawRequestDTO;
import com.hdfc.DTO.WithdrawResponseDTO;
import com.hdfc.Model.Account;
import com.hdfc.Model.Customer;
import com.hdfc.Model.Transaction;
import com.hdfc.Repositories.AccountRepository;
import com.hdfc.Repositories.CustomerRepository;
import com.hdfc.Repositories.TransactionRepository;
import com.hdfc.Utils.GeneratorUtil;
import com.hdfc.constants.MessageConstants;

// Marking the class as a Spring service
@Service
public class AdminService implements AccountService {

	// Injecting dependencies using Spring's @Autowired annotation
	@Autowired
	private GeneratorUtil generatorUtil;

	@Autowired
	private AccountRepository accountrepo;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepo;

	// Method to validate customer inputs
	private ResponseEntity<ApiResponse<CustomerResponseCredentialDTO>> validateCustomerInput(
			CustomerAccountDTO requestDto) {

		ApiResponse<CustomerResponseCredentialDTO> response;

		// Check if name is empty
		if (requestDto.getName() == null || requestDto.getName().trim().isEmpty()) {
			response = new ApiResponse<>(false, MessageConstants.NAME_CANNOT_BE_EMPTY, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Check if email is empty
		if (requestDto.getEmail() == null || requestDto.getEmail().trim().isEmpty()) {
			response = new ApiResponse<>(false, MessageConstants.EMAIL_CANNOT_BE_EMPTY, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Check if phone is empty
		if (requestDto.getPhone() == null || requestDto.getPhone().trim().isEmpty()) {
			response = new ApiResponse<>(false, MessageConstants.PHONE_CANNOT_BE_EMPTY, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Check if date of birth is null
		if (requestDto.getDob() == null) {
			response = new ApiResponse<>(false, MessageConstants.DOB_CANNOT_BE_NULL, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Check if gender is empty
		if (requestDto.getGender() == null || requestDto.getGender().trim().isEmpty()) {
			response = new ApiResponse<>(false, MessageConstants.GENDER_CANNOT_BE_EMPTY, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Check if address is empty
		if (requestDto.getAddress() == null || requestDto.getAddress().trim().isEmpty()) {
			response = new ApiResponse<>(false, MessageConstants.ADDRESS_CANNOT_BE_EMPTY, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Check if account type is empty
		if (requestDto.getAccountType() == null || requestDto.getAccountType().trim().isEmpty()) {
			response = new ApiResponse<>(false, MessageConstants.ACCOUNT_TYPE_CANNOT_BE_EMPTY, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Check if balance is negative or zero
		if (requestDto.getBalance() == 0 || requestDto.getBalance() < 0) {
			response = new ApiResponse<>(false, MessageConstants.BALANCE_MUST_BE_NON_NEGATIVE, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// If all fields are valid
		return null;
	}

	// Method to create new customer account
	@Override
	public ResponseEntity<ApiResponse<CustomerResponseCredentialDTO>> createAccount(CustomerAccountDTO requestDto) {

		// Check if email already exists
		if (customerRepository.existsByEmail(requestDto.getEmail())) {
			ApiResponse<CustomerResponseCredentialDTO> response = new ApiResponse<>(true,
					MessageConstants.EMAIL_ALREADY_EXISTS, null);
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

		// Check if phone already exists
		if (customerRepository.existsByPhone(requestDto.getPhone())) {
			ApiResponse<CustomerResponseCredentialDTO> response = new ApiResponse<>(true,
					MessageConstants.PHONE_ALREADY_EXISTS, null);
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

		// Validate input fields
		ResponseEntity<ApiResponse<CustomerResponseCredentialDTO>> validationResult = validateCustomerInput(requestDto);
		if (validationResult != null)
			return validationResult;

		// Create customer entity
		Customer customer = new Customer();
		customer.setName(requestDto.getName());
		customer.setEmail(requestDto.getEmail());
		customer.setPhone(requestDto.getPhone());
		customer.setGender(requestDto.getGender());
		customer.setDob(requestDto.getDob());
		customer.setAddress(requestDto.getAddress());

		// Generate customer ID and default password
		String customerId = generatorUtil.generateCustomerId();
		String password = "HDFC@123"; // Default password

		customer.setCustomerId(customerId);
		customer.setPassword(password);

		// Create and map account to customer
		Account account = new Account();
		account.setAccountType(requestDto.getAccountType());
		account.setBalance(requestDto.getBalance());
		account.setAccountNumber(generatorUtil.generateAccountNumber());

		account.setCustomer(customer); // Setting account's customer
		customer.setAccount(account); // Setting customer's account

		// Save customer (cascade saves account too)
		Customer savedCustomer = customerRepository.save(customer);

		// Prepare response DTO with customer credentials
		CustomerResponseCredentialDTO responseDto = new CustomerResponseCredentialDTO();
		responseDto.setCustomerId(savedCustomer.getCustomerId());
		responseDto.setPassword(savedCustomer.getPassword());

		// Send success response
		ApiResponse<CustomerResponseCredentialDTO> response = new ApiResponse<>(true,
				MessageConstants.ACCOUNT_CREATED_SUCCESS, responseDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Method to deposit money into account
	@Override
	public ResponseEntity<ApiResponse<DepositResponseDTO>> depositToAccount(DepositRequestDTO request) {
		System.out.println("AdminService.depositToAccount()");

		// Find account by account number
		Optional<Account> optionalAccount = accountrepo.findByAccountNumber(request.getAccountNumber());

		if (optionalAccount.isPresent()) {
			System.out.println("Account Number Valid............");

			Account account = optionalAccount.get();

			// First of all check the given amount
			if (request.getAmount() <= 0) {
				System.out.println("Invalid amount: " + request.getAmount());
				ApiResponse<DepositResponseDTO> invalidAmountResponse = new ApiResponse<>(false,
						"Invalid amount. Please enter a positive amount.", null);
				return new ResponseEntity<>(invalidAmountResponse, HttpStatus.BAD_REQUEST);
			}

			System.out.println("Previous Balance: " + account.getBalance());

			// Update new balance
			double newBalance = account.getBalance() + request.getAmount();
			account.setBalance(newBalance);
			accountrepo.save(account); // Save updated balance

			// Create transaction record
			Transaction transaction = new Transaction();
			transaction.setAccount(account);
			transaction.setFromAccount("ADMIN101"); // Admin as sender
			transaction.setToAccount(account.getAccountNumber());
			transaction.setTransactionType("DEPOSIT");
			transaction.setAmount(request.getAmount());
			transaction.setAvailableBalance(newBalance);
			transaction.setChannel("BRANCH");
			transaction.setInitiatedBy("ADMIN");
			transaction.setRemarks(request.getRemarks());
			transaction.setStatus("SUCCESS");
			transaction.setTransactionTime(LocalDateTime.now());

			transactionRepo.save(transaction); // Save transaction

			// Prepare deposit response DTO
			String customerName = account.getCustomer().getName();

			DepositResponseDTO responseDTO = new DepositResponseDTO(account.getAccountNumber(), customerName,
					newBalance, MessageConstants.DEPOSITE_AMMOUNT_ADDED_IN_YOURACCOUNT);

			// Wrap in ApiResponse and return
			ApiResponse<DepositResponseDTO> response = new ApiResponse<>(true,
					MessageConstants.DEPOSITE_AMMOUNT_ADDED_IN_YOURACCOUNT, responseDTO);

			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			// Account not found
			ApiResponse<DepositResponseDTO> response1 = new ApiResponse<>(false,
					MessageConstants.ACCOUNT_DOES_NOT_EXISTS, null);
			return new ResponseEntity<>(response1, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<ApiResponse<WithdrawResponseDTO>> withdrawFromAccount(WithdrawRequestDTO request) {

		// 1. Validate amount
		if (request.getAmount() <= 0) {
			return new ResponseEntity<>(new ApiResponse<>(false, "Amount must be greater than 0", null),
					HttpStatus.BAD_REQUEST);
		}

		// 2. Find account
		Optional<Account> accOpt = accountrepo.findByAccountNumber(request.getAccountNumber());
		if (accOpt.isEmpty()) {
			return new ResponseEntity<>(new ApiResponse<>(false, "Account not found", null), HttpStatus.NOT_FOUND);
		}

		Account account = accOpt.get();

		// 3. Check sufficient balance
		if (account.getBalance() < request.getAmount()) {

			// 3.1 Maintain Failed Transaction
			Transaction failedTxn = new Transaction();
			failedTxn.setAccount(account);
			failedTxn.setFromAccount(account.getAccountNumber());
			failedTxn.setToAccount(account.getAccountNumber());
			failedTxn.setTransactionType("DEBIT");
			failedTxn.setAmount(request.getAmount());
			failedTxn.setAvailableBalance(account.getBalance()); // Balance not deducted
			failedTxn.setChannel("ONLINE");
			failedTxn.setInitiatedBy(account.getCustomer().getName());
			failedTxn.setRemarks("Failed withdrawal attempt due to insufficient balance");
			failedTxn.setStatus("FAILED");
			failedTxn.setTransactionTime(LocalDateTime.now());
			transactionRepo.save(failedTxn);

			return new ResponseEntity<>(new ApiResponse<>(false, "Insufficient balance", null), HttpStatus.BAD_REQUEST);
		}

		// 4. Deduct balance
		account.setBalance(account.getBalance() - request.getAmount());
		accountrepo.save(account);

		// 5. Create SUCCESS Transaction
		Transaction txn = new Transaction();
		txn.setAccount(account);
		txn.setFromAccount(account.getAccountNumber());
		txn.setToAccount(account.getAccountNumber());
		txn.setTransactionType("DEBIT");
		txn.setAmount(request.getAmount());
		txn.setAvailableBalance(account.getBalance());
		txn.setChannel("ONLINE");
		txn.setInitiatedBy(account.getCustomer().getName());
		txn.setRemarks("Amount withdrawn by Admin");
		txn.setStatus("SUCCESS");
		txn.setTransactionTime(LocalDateTime.now());
		transactionRepo.save(txn);

		// after transaction we need to set the value for Withdraw Response

		WithdrawResponseDTO dto = new WithdrawResponseDTO();
		dto.setAccountNumber(account.getAccountNumber());
		dto.setWithdrawnAmount(request.getAmount());
		dto.setAvailableBalance(account.getBalance());
		dto.setStatus("SUCCESS");
		dto.setTransactionRef(txn.getReferenceId());

		ApiResponse<WithdrawResponseDTO> withdrawresponse = new ApiResponse<WithdrawResponseDTO>(true,
				MessageConstants.WITHDRWAL_TRANSFER_SUCCESSFULL, dto);

		//
		// // Wrap in ApiResponse and return
		// ApiResponse<DepositResponseDTO> response = new ApiResponse<>(true,
		// MessageConstants.DEPOSITE_AMMOUNT_ADDED_IN_YOURACCOUNT, responseDTO);
		//
		// return new ResponseEntity<>(response, HttpStatus.OK);
		//
		//

		return new ResponseEntity<>(withdrawresponse, HttpStatus.OK);

	}

}
