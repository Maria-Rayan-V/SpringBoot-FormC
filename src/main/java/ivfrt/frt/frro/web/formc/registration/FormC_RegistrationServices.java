package ivfrt.frt.frro.web.formc.registration;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ivfrt.frt.frro.web.user.frroservicelist.FrroListApp;
import java.net.URLEncoder;
import utilities.Util;

@Repository
public class FormC_RegistrationServices  implements FormC_RegistrationRepository{

	public static final String FILE_TYPE = "jpeg";
	@Autowired
	@Qualifier("jdbcFormC")
	private JdbcTemplate jdbcFormc;

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbcEservices;
	
	
	
	/**
	 * @deprecated
	 */
	@Override
	public Map<String, String> regCaptcha(String userid) throws Exception {

		
		String img="";
		
		String checkAttamptCountQuery="select  coalesce(max(attemptCount),0) from fr_users_salt where	"
				+ "	((DATE_PART('day',now()- entered_on)*24+DATE_PART('hour',now()- entered_on))*60+ "
				+ "		DATE_PART('minute',now()- entered_on))<5 and userid=?";
		
		  int attemptCount=jdbcEservices.queryForObject(checkAttamptCountQuery, new Object[] { userid }, Integer.class);
		  
		  if(attemptCount>5) {
			  throw new ValidationException("Please try after 5 minutes.");
			} 
		
			String captcha=Util.generateCaptchaTextMethod2(6);
			String query = "INSERT INTO fr_users_salt( "
					+ "	userid, salt, entered_on, captcha, attemptcount) "
					+ "	VALUES (?, ?, ?, ?, ?) ";
			int status=jdbcFormc.update(query, userid,userid);
			
			if(status>0) {
				img= convertStringToImage(captcha); 
			}
		Map<String, String> res=new HashedMap();
		res.put("captch", "img");
		res.put("userId", userid);
		return res;
	
	}
	
	
	/**
	 * Convert captcha value to Image
	 * @param captchaStr
	 * @return Image of captcha String
	 */
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



	
    /**
     * if user id is unique  then generate and insert captcha value in table [fr_user_salt] 
     * @param  
     * @return captcha and userID
     */

	@Override
	@Transactional
	public Map<String, String> checkAvailbleUserId(String userId) throws Exception {
		
		Map<String, String>  resObj=new HashedMap();
		boolean userExistInTemp1=checkUserExistTemp1(userId);//fr_formc_reg_otp_details
		if(userExistInTemp1) {
			throw new ValidationException("Please enter another userid.");
		}
		
		boolean userExistInTemp2=checkUserExistTemp2(userId);//fr_users_salt
		if(userExistInTemp2) {
			throw new ValidationException("Please enter another userid.");
		}
		
		boolean userExistInMain=checkUserExist(userId);//fr_users
		
		if(userExistInMain) {
			throw new ValidationException("Please enter another userid.");
		}
		
		if(userExistInMain || userExistInTemp1 || userExistInTemp2 ){
			throw new ValidationException("Please enter another userid.");
		}else {
			String captchaStr=Util.generateCaptchaTextMethod2(6);
			String captchImg= convertStringToImage(captchaStr);
			int s1 = inserFr_USER_SALT(userId, "FORMC_REG",captchaStr);
			int s2=insertFR_FORMC_REG_OTP_DETAILS(userId); 
			if (s1>0 && s2>0) {
				
				resObj.put("captcha", captchImg);
				resObj.put("UserId", userId);
				
			}else {
				throw new ValidationException("Somthing went wrong.");
			}
		}
		
		return resObj;
	}


	private boolean checkUserExistTemp2(String userId) {

		String foundquery="SELECT COUNT(*) FROM fr_users_salt WHERE userid=? ";
		int found= jdbcFormc.queryForObject(foundquery, new Object[] { userId }, Integer.class);
		
		if(found>0) {
			return true;
		}else {
			return false;
		}
	}


	private boolean checkUserExistTemp1(String userId) {

		String foundquery="SELECT COUNT(*) FROM fr_formc_reg_otp_details WHERE user_id=? ";
		int found= jdbcFormc.queryForObject(foundquery, new Object[] { userId }, Integer.class);
		
		if(found>0) {
			return true;
		}else {
			return false;
		}
	}


	private int insertFR_FORMC_REG_OTP_DETAILS(String userId) {
		
		String query = "insert into fr_formc_reg_otp_details (user_id) values(?)";
		int updatekey = jdbcFormc.update(query,userId);
		return updatekey;

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

	

	private int imposeRateLimit(String user) {
		String query = "with t1 as (select ((DATE_PART('day',now()- entered_on)*24+DATE_PART('hour',now()- entered_on))*60+ DATE_PART('minute',now()- entered_on)) as req "
				+ "from fr_users_salt where userid=?) select count(*) from t1 where t1.req>1 ";
		return jdbcEservices.queryForObject(query, new Object[] { user.toLowerCase() }, Integer.class);
		
	}
	
	public void deleteSaltData_fr_users_salt(String user) {

		String deleteQuery = "delete from fr_users_salt where userid = ?";
		jdbcFormc.update(deleteQuery, user);
	}

	private boolean checkUserExist(String userId) {
		
		String foundquery="SELECT COUNT(*) FROM fr_users WHERE userid=? ";
		int found= jdbcFormc.queryForObject(foundquery, new Object[] { userId }, Integer.class);
		
		if(found>0) {
			return true;
		}else {
			return false;
		}
	}









	@Override
	@Transactional
	public FormC_Registration submitRegDetails(FormC_Registration regDetails) {
		
		 FormC_Registration resObj=new FormC_Registration();
		 
		// String frroCode=fetchFrroCode(regDetails.getAccomState(),regDetails.getAccomCityDist());
		 String frroCode=regDetails.getFrroTypeCode();
		 String acco_code=generateAccoCode(frroCode);
		 regDetails.setAcco_code(acco_code);
		 regDetails.setPassword(null);
		 regDetails.setConfirmPassword(null);
		 
		 insertIn_FR_ACCOMODATOR(regDetails,acco_code,frroCode);
		 inserIn_FR_USER(regDetails,frroCode);
		 insertIn_FR_ACCO_REG_FORMC(regDetails,acco_code,frroCode);
		 insertIn_FR_ACCOMODATOR_OWNER(regDetails,acco_code,frroCode);
		 
		return regDetails;
	}



	private void insertIn_FR_ACCOMODATOR_OWNER(FormC_Registration regDetails, String acco_code, String frroCode) {
		
		List<OwnerDetails> ownerDetailsList=regDetails.getOwnerDetails();
		int ctrl=1;
		for (OwnerDetails od : ownerDetailsList) {
			
		 String	query = "INSERT INTO fr_accomodator_owner(acco_code, owner_code, owner_name, owner_address, city_district, state_code, phone_no, mobile_no, email_id, entered_by,client_from,frro_fro_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		 jdbcFormc.update(query,acco_code,
				 String.valueOf(ctrl),
				 od.getName(),
				 od.getAddress(),
				 od.getCityDist(),
				 od.getState(),
				 od.getPhoneNum(),
				 od.getMobile(),
				 od.getEmailId(),
				 "",
				 regDetails.getClientIp(),
				 frroCode);
				 
		}
		
	}



	private void insertIn_FR_ACCO_REG_FORMC(FormC_Registration regDetails, String acco_code, String frroCode) {
		String query = "INSERT INTO fr_acco_reg_formc(userid,gender, dob, mobile_no, nationality, acco_code, entered_by, client_from,frro_fro_code,security_ques,security_ans) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,'N','NA')";
		jdbcFormc.update(query,
				regDetails.getUserId(),
				regDetails.getGender(),
				str_to_date_formc(regDetails.getDob()),
				regDetails.getMobile(),
				regDetails.getNationality(),
				acco_code,
				"FormC User",
				regDetails.getClientIp(),
				frroCode);
	}


	private void inserIn_FR_USER(FormC_Registration regDetails, String frroCode) {
		String query = "INSERT INTO fr_users( userid, password, user_name, designation, e_mail_id, phone_no, user_type_code,frro_fro_code, entered_by, active, inactive_date, client_from, password_updated_on ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, clock_timestamp())";
		jdbcFormc.update(query,
				regDetails.getUserId(),
				regDetails.getPassword(),
				regDetails.getName(),
				regDetails.getDesignation(),
				regDetails.getEmailId(),
				regDetails.getPhnNumber(),
				"5",
				frroCode,
				"FormC User",
				"N",
				null,
				regDetails.getClientIp());
	}


	private void insertIn_FR_ACCOMODATOR(FormC_Registration regDetails, String acco_code,String frroCode) {
		
		String query = "INSERT INTO fr_accomodator(acco_code, acco_name, acco_address, city_district, state_code,acco_grade, phone_no,"
				+ "  mobile_no, email_id, entered_by,acc_type_code, capacity , frro_fro_code, client_from) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	
		jdbcFormc.update(query
				,acco_code,
				regDetails.getAccomName(),
				regDetails.getAccomAddress(),
				regDetails.getAccomCityDist(),
				regDetails.getAccomState(),
				regDetails.getAccomodationGrade(),
				regDetails.getAccomPhoneNum(),
				regDetails.getAccomMobile(),
				regDetails.getAccomEmail(),
				"FormC User",
				regDetails.getAccomodationType(),
				regDetails.getAccomCapacity(),
				frroCode,
				regDetails.getClientIp());
	
	}


	private String generateAccoCode(String frroCode) {
		int count=0;
		String randvalue=randGenerator(4);
		do
		{
			String query="select count(*) from fr_accomodator where acco_code =? and frro_fro_code =? ";
			count=jdbcFormc.queryForObject(query, new Object[] {randvalue,frroCode }, Integer.class);
		}while(count>0);
		
		return randvalue;
	}


	private String fetchFrroCode(String accomState, String accomCityDist) {
		String query = "select frro_fro_code from fr_frro_fro_list where state_code = ? and city_district_code = ? and active='Y' ";
		return jdbcFormc.queryForObject(query, new Object[] {accomState,accomCityDist }, String.class);
	}


	@Override
	public boolean validateMobileOtp(String userId, String mobile, String mobile_otp) {
		String foundquery="select count(*) from fr_formc_reg_otp_details where mobile_no=? and mobile_otp=? and user_id=? ";
		int found= jdbcFormc.queryForObject(foundquery, new Object[] {mobile,mobile_otp, userId }, Integer.class);
		
		if(found>0) {
			return true;
		}else {
			return false;
		}
	}



	@Override
	public boolean validateEmailOtp(String userId, String email, String email_otp) {
		String foundquery="select count(*) from fr_formc_reg_otp_details where e_mail_id=? and e_mail_id_otp=? and user_id=? ";
		int found= jdbcFormc.queryForObject(foundquery, new Object[] {email,email_otp, userId }, Integer.class);
		
		if(found>0) {
			return true;
		}else {
			return false;
		}
	}



	@Override
	public Map<String, String> generateMobileOtp(String userId, String mobileNo) throws Exception {
		
		boolean userExistInTemp1=checkUserExistTemp1(userId);
		if(!userExistInTemp1) {
			throw new ValidationException("Invalid user id.");
		}
		
		//block method call for 2 minute
		int minute=getWhenMobileOTPCall(userId);
		if(minute<2) {
			throw new ValidationException("Please try after some time.");
		}
		
		Map<String, String> resObj=new HashedMap();
		String mobileOTP=Util.generateCaptchaTextMethod2(4);
		
		int ststus=updateMobileOtpDetail(userId,mobileNo,mobileOTP);
		 
		if(ststus>0) {
			
			String message="OTP is "+mobileOTP+" for  validate  mobile number in Form C Registration.";
		
			//SendSms(message,mobileNo);
			
			resObj.put("userId", userId);
			resObj.put("mobileNo", mobileNo);
			resObj.put("message", "OTP successfully send to your Mobile no.");
		}
		return resObj;
	}

    private int getWhenMobileOTPCall(String userId) {
		String query="select ((DATE_PART('day',now()- mobile_otp_date)*24+DATE_PART('hour',now()- mobile_otp_date))*60+ DATE_PART('minute',now()- mobile_otp_date)) as req  from fr_formc_reg_otp_details where user_id=? ";
		
		String res=jdbcFormc.queryForObject(query, new Object[] { userId }, String.class); 
		
		if(res==null) {
			return  5;
		}else {
			return (int)Double.parseDouble(res);
		}
		
	}


	/**
     *  Record Insert in Table [fr_formc_reg_otp_details]
     * @param userId 
     * @param mobileNo
     * @param mobileOTP
     * @return if successfully inserted then 1 else 0.
     */
	private int updateMobileOtpDetail(String userId, String mobileNo, String mobileOTP) {
		
		String query="UPDATE fr_formc_reg_otp_details SET  mobile_no=?, mobile_otp=?, mobile_otp_date=now() WHERE user_id=? ";
		return jdbcFormc.update(query, mobileNo,mobileOTP,userId);
		 
	}



	@Override
	public Map<String, String> generateEmailOtp(String userId, String emailId) throws Exception {

		boolean userExistInTemp1=checkUserExistTemp1(userId);
		if(!userExistInTemp1) {
			throw new ValidationException("Invalid user id.");
		}
		int minute=getWhenEmailOTPCall(userId);
		if(minute<2) {
			throw new ValidationException("Please try after some time.");
		}
		
		
		Map<String, String> resObj=new HashedMap();
		String emailOTP=Util.generateCaptchaTextMethod2(4);
		
		int ststus=insertEmailOtpDetail(userId,emailId,emailOTP);
		 
		if(ststus>0) {
			//send otp api call
			
			resObj.put("userId", userId);
			resObj.put("emailId", emailId);
			resObj.put("message", "OTP successfully send to your Email id.");
			
		}
		
		return resObj;
	
	}



	private int getWhenEmailOTPCall(String userId) {

		String query="select ((DATE_PART('day',now()- email_otp_date)*24+DATE_PART('hour',now()- email_otp_date))*60+ DATE_PART('minute',now()- email_otp_date)) as req  from fr_formc_reg_otp_details where user_id=? ";
		String res=jdbcFormc.queryForObject(query, new Object[] { userId }, String.class); 
		
		if(res==null) {
			return  5;
		}else {
			return (int)Double.parseDouble(res);
		}
		
	
	}


	private int insertEmailOtpDetail(String userId, String emailId, String emailOTP) {

		
		String query="UPDATE  fr_formc_reg_otp_details SET  e_mail_id=?, e_mail_id_otp=?, email_otp_date=now() WHERE user_id=?";
		return jdbcFormc.update(query, emailId,emailOTP,userId);
	}

	 /**
     * @param   message and mobile no.
     * @return  void.
     */
	public void SendSms(String message,String mobileno) 
	{
		try 
		{
			message=URLEncoder.encode(message,"UTF-8");
			String https_url = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=frro.sms&pin=Dp%235@sa7d&message="+message+"&mnumber="+mobileno+"&signature=BHARAT&dlt_entity_id=1001069858563293105&dlt_template_id=1007118676445399578";
			//System.out.println("URL--"+https_url);
			URL url;
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, // key manager
					trust_mgr, // trust manager
					new SecureRandom()); // random number generator
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			print_content(con);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
}

	public TrustManager[] get_trust_mgr() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String t) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String t) {
			}
		} };
		return certs;
}
	
	public void print_content(HttpsURLConnection con) {
		if (con != null) {

			try 
			{
				//System.out.println("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String input;
				while ((input = br.readLine()) != null) {
					//System.out.println("Input check-----"+input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}


	@Override
	public String fetchCaptcha(String userId) {
		String query="select captcha from fr_users_salt where   userid=? ";
		return jdbcFormc.queryForObject(query, new Object[] { userId }, String.class);
	
	}
	
	

	public String randGenerator(int range)
	{
		String randString = "";
		if (range <= 0)
		{
		    return randString;
		}
		StringBuffer sb = new StringBuffer();
		String block = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		try 
		{
			for(int i=0;i<range;i++)
			{
				sb.append(Character.toString(block.charAt(random.nextInt(block.length()-1))));
			}
			randString=sb.toString();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
		return randString;
	}


	@Override
	public List<Map<String, Object>> frroList(String stateCode, String cityDist) {
		
		Map<String, Object> resObj=new HashedMap();
		
		String query="select frro_fro_code,frro_fro_desc from fr_frro_fro_list where state_code = ? and city_district_code = ? and active='Y'  and formc_online_flag='Y' " ;
	
		    return jdbcFormc.queryForList(query, stateCode,cityDist);
		
		
	}
	
	
	public java.sql.Date str_to_date_formc(String data) {
        java.sql.Date DOBdate_sql = null;

        if ((data == "") || (data == "null") || (data == null)) {
                return DOBdate_sql;
        }
        try {
                DateFormat formatter = null;
                formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
                java.util.Date DOBdate_j = null;
                DOBdate_j = formatter.parse(data);
                DOBdate_sql = new java.sql.Date(DOBdate_j.getTime());
        } catch (Exception e) {
                System.out.println("Exception in Registration src/frro.Utility.java -> str_to_date_eservice() ");
                // e.printStackTrace();
                return null;
        }
        return DOBdate_sql;
}


	@Override
	@Transactional
	public FormC_UserRegistration submitUserDetails(@Valid FormC_UserRegistration formCUser) {
		
		if(!formCUser.getPassword().equals(formCUser.getConfirmPassword())) {
			throw new ValidationException("Password and Confirm Password must be same.");
		}
		 inserIn_FR_USER(formCUser);
		 insertIn_FR_ACCO_REG_FORMC(formCUser);
		 updateInFR_FORMC_REG_OTP_DETAILS(formCUser);
		 formCUser.setPassword(null);
		 formCUser.setConfirmPassword(null);
		return formCUser;
	}


	private void updateInFR_FORMC_REG_OTP_DETAILS(@Valid FormC_UserRegistration formCUser) {
		String sql="update fr_formc_reg_otp_details set isemail_otp_approved=?, ismobile_otp_approved=? where user_id=?";
		jdbcFormc.update(sql,formCUser.getIsEmailVerified(),formCUser.getIsMobileVerified(),formCUser.getUserId());
	}


	private void insertIn_FR_ACCO_REG_FORMC(@Valid FormC_UserRegistration formCUser) {
		

		String query = "INSERT INTO fr_acco_reg_formc(userid, mobile_no, entered_by, client_from) VALUES (?, ?, ?, ?)";
		jdbcFormc.update(query,
				formCUser.getUserId(),
				formCUser.getMobile(),
				formCUser.getClientIp(),
				"FormC User"
				);
	
		
	}


	private void inserIn_FR_USER(@Valid FormC_UserRegistration formCUser) {

		String query = "INSERT INTO fr_users( userid, password, user_name, e_mail_id, active,"
				+ " client_from,user_type_code,entered_by, password_updated_on ) VALUES (?, ?, ?, ?, ?, ?,?,?, clock_timestamp())";
		jdbcFormc.update(query,
				formCUser.getUserId(),
				formCUser.getPassword(),
				formCUser.getUserName(),
				formCUser.getEmailId(),
				"N",
				formCUser.getClientIp(),
				"5",
				"FormC User");
	}
}
