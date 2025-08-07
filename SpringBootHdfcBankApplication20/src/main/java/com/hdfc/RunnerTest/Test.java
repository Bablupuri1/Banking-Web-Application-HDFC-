package com.hdfc.RunnerTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hdfc.Utils.GeneratorUtil;

@Component
public class Test  implements CommandLineRunner{

	@Autowired
	GeneratorUtil test;

	
@Override
public void run(String... args) throws Exception {

	String res=test.generateAccountNumber();
	System.out.println("Account Number:"+res);
	String customerid=test.generateCustomerId();
	System.out.println("Customer id:"+customerid);
}
}
