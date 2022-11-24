package utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomSaltGenerator {

	private static final Random RANDOM = new SecureRandom();
	public static String generateSalt() throws NoSuchAlgorithmException
	{
		char[] CHARSET_AZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random random = new SecureRandom();
		char[] result = new char[6];
		
		for (int i = 0; i < result.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_AZ.length);
			result[i] = CHARSET_AZ[randomCharIndex];
		}
		return new String(result);
	}
	
	public static String generatetoken() throws NoSuchAlgorithmException
	{
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		 byte[] bSalt = new byte[25];
		 random.nextBytes(bSalt);
		 String randSalt=bSalt.toString();
		 return randSalt;
	}
	
	public static String generatesecureRandomSalt() throws NoSuchAlgorithmException
	{
		char[] CHARSET_AZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] CHARSET_az = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] CHARSET_09 = "0123456789".toCharArray();
		char[] CHARSET_special = "!@#$%^&*".toCharArray();
		
		Random random = new SecureRandom();
		char[] result = new char[12];
		char[] result1 = new char[10];
		char[] result2 = new char[10];
		//char[] result3 = new char[6];
		for (int i = 0; i < result.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_AZ.length);
			result[i] = CHARSET_AZ[randomCharIndex];
		}
		for (int i = 0; i < result1.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_az.length);
			result1[i] = CHARSET_az[randomCharIndex];
		}
		for (int i = 0; i < result2.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_09.length);
			result2[i] = CHARSET_09[randomCharIndex];
		}
		/*for (int i = 0; i < result3.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(CHARSET_special.length);
			result3[i] = CHARSET_special[randomCharIndex];
		}*/
		//System.out.println("new ::; " +new String(result)+ new String(result1)+ new String(result2));
		return new String(result)+ new String(result1)+ new String(result2);		
	}
	
	 public  String encryptSHA256(String input) 
	    { 
	        try { 
	            // getInstance() method is called with algorithm SHA-512 
	            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
	  
	            // digest() method is called 
	            // to calculate message digest of the input string 
	            // returned as array of byte 
	            byte[] messageDigest = md.digest(input.getBytes()); 
	  
	            // Convert byte array into signum representation 
	            BigInteger no = new BigInteger(1, messageDigest); 
	  
	            // Convert message digest into hex value 
	            String hashtext = no.toString(16); 
	  
	            // Add preceding 0s to make it 32 bit 
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	  
	            // return the HashText 
	            return hashtext; 
	        } 
	  
	        // For specifying wrong message digest algorithms 
	        catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 
	    }
	
	 public  String getMd5(String input)
	    {
	        try {
	  
	            // Static getInstance method is called with hashing MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	  
	            // digest() method is called to calculate message digest
	            //  of an input digest() return array of byte
	            byte[] messageDigest = md.digest(input.getBytes());
	  
	            // Convert byte array into signum representation
	            BigInteger no = new BigInteger(1, messageDigest);
	  
	            // Convert message digest into hex value
	            String hashtext = no.toString(16);
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } 
	  
	        // For specifying wrong message digest algorithms
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        } 
	    }
	
}
