package com.hdfc.constants;

/**
 * This class contains all static, reusable string messages used across
 * the application for API responses, validations, logging, and errors.
 */
public class MessageConstants {

    //  Success Messages
    public static final String ACCOUNT_CREATED_SUCCESS = "Account created successfully.";
    public static final String CUSTOMER_CREATED_SUCCESS = "Customer profile created.";
    public static final String FETCH_SUCCESS = "Data fetched successfully.";
    public static final String UPDATE_SUCCESS = "Data updated successfully.";
    public static final String DELETE_SUCCESS = "Data deleted successfully.";

    
    
    public static final String NAME_CANNOT_BE_EMPTY = "Name cannot be empty";
    public static final String EMAIL_CANNOT_BE_EMPTY = "Email cannot be empty";
    public static final String PHONE_CANNOT_BE_EMPTY = "Phone cannot be empty";
    public static final String DOB_CANNOT_BE_NULL = "DOB cannot be null";
    public static final String GENDER_CANNOT_BE_EMPTY = "Gender cannot be empty";
    public static final String ADDRESS_CANNOT_BE_EMPTY = "Address cannot be empty";
    public static final String ACCOUNT_TYPE_CANNOT_BE_EMPTY = "Account type cannot be empty";
    public static final String BALANCE_MUST_BE_NON_NEGATIVE = "Balance must be non-negative";
    public static final String DEPOSITE_AMMOUNT_ADDED_IN_YOURACCOUNT="Deposit Successfully...";
    public static final String ACCOUNT_DOES_NOT_EXISTS="Account Number Does Not Exit Invalide Account Number..";

    // ⚠️ Validation or Business Rule Messages
    public static final String EMAIL_ALREADY_EXISTS = "Email is already registered.";
    public static final String PHONE_ALREADY_EXISTS = "Phone number already in use.";
    public static final String INSUFFICIENT_BALANCE = "Insufficient balance for this transaction.";
    public static final String INVALID_ACCOUNT_TYPE = "Invalid account type provided.";
    public static final String INVALID_CUSTOMER_ID = "Invalid customer ID.";
    public static  final String MONEY_TRANSFER_SUCCESSFULL="Money TransferSuccessfull..";
    public static  final String WITHDRWAL_TRANSFER_SUCCESSFULL="Money TransferSuccessfull..";

    

    // ❌ Error Messages
    public static final String ACCOUNT_CREATION_FAILED = "Failed to create account.";
    public static final String CUSTOMER_CREATION_FAILED = "Failed to create customer.";
    public static final String INTERNAL_SERVER_ERROR = "Something went wrong. Please try again later.";
    public static final String NOT_FOUND = "Requested resource not found.";
    public static final String UNAUTHORIZED = "Unauthorized access.";

    // 🔒 Auth Messages (if login/signup added later)
    public static final String LOGIN_SUCCESS = "Login successful.";
    public static final String LOGIN_FAILED = "Invalid credentials.";
    public static final String LOGOUT_SUCCESS = "Logged out successfully.";

    // Utility class: prevent instantiation
    private MessageConstants() {}
}
