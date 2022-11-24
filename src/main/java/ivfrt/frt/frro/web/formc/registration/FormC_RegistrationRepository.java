package ivfrt.frt.frro.web.formc.registration;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

public interface FormC_RegistrationRepository {

	public Map<String,String> regCaptcha(String userid)throws Exception;
	//Response Captcha and User Id
	public Map<String,String> checkAvailbleUserId(String userId) throws Exception;  
	public FormC_Registration submitRegDetails(FormC_Registration regBasicDetails);
	public Map<String,String> generateMobileOtp(String userId,String mobileNo) throws Exception;
	public Map<String,String> generateEmailOtp(String userId,String emailId)throws Exception;
	public boolean validateMobileOtp(String userId, String mobile, String mobile_otp);
	public boolean validateEmailOtp(String userId, String mobile, String mobile_otp);
	public String fetchCaptcha(String userId);
	public List<Map<String,Object>> frroList(String stateCode,String cityDist);
	public FormC_UserRegistration submitUserDetails(@Valid FormC_UserRegistration formCUser);
	
	
}
