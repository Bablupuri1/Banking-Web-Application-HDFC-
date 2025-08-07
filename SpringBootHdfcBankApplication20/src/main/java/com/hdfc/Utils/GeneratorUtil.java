package com.hdfc.Utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class GeneratorUtil {

    private static final AtomicLong custIdCounter = new AtomicLong(1000); // starts from 1000

    // This will only fill last 10 digits (because prefix is 6 digits)
    private static final AtomicLong accNoCounter = new AtomicLong(1000000000); // 10-digit counter

    private static final String HDFC_PREFIX = "524501"; // Can be used as Bank+Branch code prefix

    // Generates something like: CUST1001, CUST1002, ...
    public String generateCustomerId() {
        return "CUST" + custIdCounter.getAndIncrement();
    }

    // Generates something like: 5245011234567890 (16 digits)
    public String generateAccountNumber() {
        String prefix = "5240"; // HDFC-style starting digits
        String time = String.valueOf(System.currentTimeMillis()).substring(5, 12); // 7-digit time-based
        int random = new Random().nextInt(900000) + 100000; // 5-digit random

        return prefix + time + random; // 4 + 7 + 5 = 16 digits
    }

}
