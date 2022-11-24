package ivfrt.frt.frro.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		for(int i = 1; i<=10; i++) {
		System.out.println(encoder.encode("password@!23")); 	
		}
		System.out.println("-----------------------------"); 
		System.out.println(encoder.upgradeEncoding("$2a$10$Nd3uPrrlOfMcFnhJZ2hiHeuiIbrfRN06aJJepz2C/Stg5momOxtDC")); 
		System.out.println(encoder.matches("password@!23", "$2a$10$Nd3uPrrlOfMcFnhJZ2hiHeuiIbrfRN06aJJepz2C/Stg5momOxtDC")); 
	
	}

}
