package utilities;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Util {
 
public static String generateCaptchaTextMethod1() throws NoSuchAlgorithmException    {
 
	SecureRandom rdm = SecureRandom.getInstance("SHA1PRNG");//Random rdm=new Random();
        int rl=rdm.nextInt(); // Random numbers are generated.
        String hash1 = Integer.toHexString(rl); // Random numbers are converted to Hexa Decimal. 
        return hash1;
 
}
 
public static String generateCaptchaTextMethod2(int captchaLength) throws NoSuchAlgorithmException   {
 
     String saltChars = "23456789ABCDE23456789STUVWXYZ23456789FGHJKLMNPQR";
     StringBuffer captchaStrBuffer = new StringBuffer();
     SecureRandom rnd = SecureRandom.getInstance("SHA1PRNG");//java.util.Random rnd = new java.util.Random();
 
            // build a random captchaLength chars salt
            while (captchaStrBuffer.length() < captchaLength)
            {
                int index = (int) (rnd.nextFloat() * saltChars.length());
                captchaStrBuffer.append(saltChars.substring(index, index+1));
            }
 
        return captchaStrBuffer.toString();
 
}
 

}
