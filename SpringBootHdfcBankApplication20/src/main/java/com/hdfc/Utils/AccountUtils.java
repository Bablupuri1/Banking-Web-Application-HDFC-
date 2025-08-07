package com.hdfc.Utils;

public class AccountUtils {
    public static String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) {
            return "XXXX";
        }
        return "XXXXXX" + accountNumber.substring(accountNumber.length() - 4);
    }
}
