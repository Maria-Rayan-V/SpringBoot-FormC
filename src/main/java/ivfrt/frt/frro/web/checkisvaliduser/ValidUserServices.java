package ivfrt.frt.frro.web.checkisvaliduser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.validation.ValidationException;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import utilities.Util;

@Repository
public class ValidUserServices implements ValidUserRepository {

	public static final String FILE_TYPE = "jpeg";
	@Autowired
	@Qualifier("jdbcFormC")
	private JdbcTemplate jdbcFormc;

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbcEservices;

	@Override
	public ValidUser validUser(String user) throws Exception {
		ValidUser resObj = null;
		String salt = "";
		int status = checkValidUser(user);

		if (status > 0) {
			salt = getSaltString();
			String captchaStr=Util.generateCaptchaTextMethod2(6);
			String img= convertStringToImage(captchaStr); 
			
			System.out.println("---------------------------------------"+img);
			int s = inserFr_USER_SALT(user, salt,captchaStr);
			if (s > 0) {
				
				
				resObj = new ValidUser(user, salt, "VALIDUSER");
				resObj.setCaptcha(img);
			} else {
				throw new Exception();
			}
		} else {
			resObj = new ValidUser(user, "", "INVALIDUSER");
		}

		return resObj;
	}

	private int inserFr_USER_SALT(String user, String salt,String captcha) {
		

		String foundquery = "select count(*) from fr_users_salt where userid=? ";
		int found= jdbcFormc.queryForObject(foundquery, new Object[] { user }, Integer.class);
	
		if(found!=0) {
			//int reqfound=imposeRateLimit(user); //Request after 1 minute for a User
		
			
			//if(reqfound==0) {
			//	throw new ValidationException("Please try after some times.");	
			//}
		
		deleteSaltData_fr_users_salt(user);
		
	   }
		String query = "insert into fr_users_salt(userid,salt,captcha) values(?,?,?)";
		int updatekey = jdbcFormc.update(query, user, salt,captcha);
		return updatekey;

	}

	
	private int inserFr_USER_SALT(String user, String salt) {
		
		
		
		
		deleteSaltData_fr_users_salt(user);
		String query = "insert into fr_users_salt(userid,salt) values(?,?)";
		int updatekey = jdbcFormc.update(query, user, salt);
		return updatekey;

	}
	private int imposeRateLimit(String user) {
		String query = "with t1 as (select ((DATE_PART('day',now()- entered_on)*24+DATE_PART('hour',now()- entered_on))*60+ DATE_PART('minute',now()- entered_on)) as req "
				+ "from fr_users_salt where userid=?) select count(*) from t1 where t1.req>1 ";
		return jdbcEservices.queryForObject(query, new Object[] { user.toLowerCase() }, Integer.class);
		
	}

	@Override
	public void deleteSaltData_fr_users_salt(String user) {

		String deleteQuery = "delete from fr_users_salt where userid = ?";
		jdbcFormc.update(deleteQuery, user);
	}

	private int checkValidUser(String userid) {
		String query = "select count(*) from fr_users where userid=? ";
		return jdbcFormc.queryForObject(query, new Object[] { userid }, Integer.class);
	}

	private int checkValidUser_FROM_eservice(String userid) {
		String query = "select count(*) from e_users where lower(emailid)=? ";
		return jdbcEservices.queryForObject(query, new Object[] { userid.toLowerCase() }, Integer.class);
	}

	protected String getSaltString() {
		String SALTCHARS = "[ABC]DEFGH#IJKL%$@MNOPQRSTUVWXYZ12#34567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 9) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	@Override
	public ValidUser validUser_FromEservice(String username) throws Exception {

		ValidUser resObj = null;
		String salt = "";
		int status = checkValidUser_FROM_eservice(username);

		if (status > 0) {
			salt = getSaltString();
			String captchaStr=Util.generateCaptchaTextMethod2(6);
			String img= convertStringToImage(captchaStr);
			
			int s = inserFr_USER_SALT(username, salt,captchaStr);
			//int s = inserFr_USER_SALT(username, salt);
			if (s > 0) {
				resObj = new ValidUser(username, salt, "VALIDUSER");
				resObj.setCaptcha(img);
			} else {
				throw new Exception();
			}
		} else {
			resObj = new ValidUser(username, "", "INVALIDUSER");
		}

		return resObj;

	}

	private String convertStringToImage(String captchaStr) {
		String img=null;
		try {
			
			 int width=100;      int height=40;
             Color bg = new Color(255,255,255);
             Color fg = new Color(178,70,70);
             Font font = new Font("Courier", Font.BOLD, 20);
             BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.OPAQUE);
             Graphics g = cpimg.createGraphics();

             g.setFont(font);
             g.setColor(bg);
             g.fillRect(0, 0, width, height);
             g.setColor(fg);
             g.drawString(captchaStr,10,25); 
			
             img=imageToBase64String(cpimg, FILE_TYPE);
			
		}catch (Exception e) {
			
			
		}
		return img;
	}


    public static String imageToBase64String(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {//from   www .java2s.com
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            imageString = Base64.getEncoder().encodeToString(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

	@Override
	public String fetchCaptcha(String userid) {
		String query = "select captcha from fr_users_salt where userid=? order by entered_on desc limit 1";
		return jdbcFormc.queryForObject(query, new Object[] { userid }, String.class);
	
	}

	@Override
	public String refreshCaptcha(String userid) throws Exception {
		
		String img="";
		
		String checkAttamptCountQuery="select  coalesce(max(attemptCount),0) from fr_users_salt where	"
				+ "	((DATE_PART('day',now()- entered_on)*24+DATE_PART('hour',now()- entered_on))*60+ "
				+ "		DATE_PART('minute',now()- entered_on))<5 and userid=?";
		
		  int attemptCount=jdbcEservices.queryForObject(checkAttamptCountQuery, new Object[] { userid }, Integer.class);
		  
		  if(attemptCount>5) {
			  throw new ValidationException("Please try after 5 minutes.");
			} 
		
			String captcha=Util.generateCaptchaTextMethod2(6);
			String query = "UPDATE  fr_users_salt SET captcha=?,entered_on=now(), "
					+ " attemptCount=(select  coalesce (max(attemptCount),0)+1 from fr_users_salt where userid=? )"
					+ " where userid = ?";
			int status=jdbcFormc.update(query,captcha, userid,userid);
			
			if(status>0) {
				img= convertStringToImage(captcha); 
			}
		
		return img;
	}
}
