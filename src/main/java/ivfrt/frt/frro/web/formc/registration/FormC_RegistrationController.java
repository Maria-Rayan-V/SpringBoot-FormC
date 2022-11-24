package ivfrt.frt.frro.web.formc.registration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.masters.model.CityDistrict;
import ivfrt.frt.frro.web.masters.model.Country;
import ivfrt.frt.frro.web.utilities.ConstantUtil;


@RestController
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class FormC_RegistrationController {


	@Autowired
	private FormC_RegistrationRepository fromCRegRepo;
	
	
	
	@GetMapping("/valid-user/formc/reg-captcha/{userid}")
	public ResponseEntity<?> generateCaptcha(@PathVariable 
			 @Valid @NotNull @Size(max =20,min = 8,message = "User id : Minimum 8 charecters and Maximum 20 charecter allowed." )
	         @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "User ID is case sensitive, minimum one alphabetic character and minimum one numeric character")
	   String userid) {
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			Map<String,String> vu = fromCRegRepo.checkAvailbleUserId(userid);
			return new ResponseEntity<Map<String,String>>(vu, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/valid-user/formc/mobile-otp/{userid}/{mobile_no}")
	public ResponseEntity<?> generateMobileOtp(@PathVariable
			 @Valid @NotNull @Size(max =20,min = 8,message = "User id : Minimum 8 charecters and Maximum 20 charecter allowed." )
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "User ID is case sensitive, minimum one alphabetic character and minimum one numeric character")
			String userid,@PathVariable 
			@Valid @NotNull @Size(max = 10,min = 10,message = "Mobile Number must be 10 digit") 
	        @Pattern(regexp = ConstantUtil.NUM, message = "Only Number allowed.")
	String mobile_no) {
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			Map<String,String> mobileOtpObj = fromCRegRepo.generateMobileOtp(userid, mobile_no);
			return new ResponseEntity<Map<String,String>>(mobileOtpObj, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/valid-user/formc/email-otp/{userid}/{e_mail_id}")
	public ResponseEntity<?> generateEmailOtp(@PathVariable 
			 @Valid @NotNull @Size(max =20,min = 8,message = "User id : Minimum 8 charecters and Maximum 20 charecter allowed." )
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "User ID is case sensitive, minimum one alphabetic character and minimum one numeric character")
			String userid,@PathVariable @NotNull(message = "Email id required.") @Email(message = "Invalid Email id.") String e_mail_id) {
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			Map<String,String> mobileOtpObj = fromCRegRepo.generateEmailOtp(userid, e_mail_id);
			return new ResponseEntity<Map<String,String>>(mobileOtpObj, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@PostMapping("/valid-user/formc/submit-user-details")
	public ResponseEntity<?> submitUserDetails(HttpServletRequest req, @Valid @RequestBody FormC_UserRegistration formCUser)
			throws Exception {

		String dbCaptcha= fromCRegRepo.fetchCaptcha(formCUser.getUserId());
		
		
		HashMap<String, String> resultMap = new HashMap<>(); 
		FormC_UserRegistration resObj =new FormC_UserRegistration();
		try {
			if(!dbCaptcha.equals(formCUser.getCaptcha())) {
				throw new ValidationException("Invalid captcha.");
			}
			
			boolean validMobileOtp=fromCRegRepo.validateMobileOtp(formCUser.getUserId(),formCUser.getMobile(),formCUser.getMobile_otp());
			boolean validEmailOtp=fromCRegRepo.validateEmailOtp(formCUser.getUserId(),formCUser.getEmailId(),formCUser.getEmail_otp());
		    boolean eitherEmailOrMobileVerified=false;
			
			if(!validMobileOtp) {
				formCUser.setIsMobileVerified("N");
			}else {
				formCUser.setIsMobileVerified("Y");
				eitherEmailOrMobileVerified=true;
			}
			if(!validEmailOtp) {
				formCUser.setIsEmailVerified("N");
			}else {
				formCUser.setIsEmailVerified("Y");
				eitherEmailOrMobileVerified=true;
			}
			if(eitherEmailOrMobileVerified) {
				resObj=fromCRegRepo.submitUserDetails(formCUser);
				resultMap.put("userId",resObj.getUserId() );
				resultMap.put("isEmailVerified",resObj.getIsEmailVerified() );
				resultMap.put("isMobileVerified",resObj.getIsMobileVerified());
				resultMap.put("message","Userid created successfully.");
			}else {
				throw new ValidationException("Either Email OTP or Mobile OTP is required.");
			}

		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/formc/submit-accomdator-details")
	public ResponseEntity<?> submitAccomDetails(HttpServletRequest req, @Valid @RequestBody FormC_Registration formCReg)
			throws Exception {

		String dbCaptcha= fromCRegRepo.fetchCaptcha(formCReg.getUserId());
		if(!dbCaptcha.equals(formCReg.getCaptcha())) {
			throw new ValidationException("Invalid captcha.");
		}
		
		HashMap<String, String> resultMap = new HashMap<>(); 
		FormC_Registration resObj =new FormC_Registration();
		try {
			
			boolean validMobileOtp=fromCRegRepo.validateMobileOtp(formCReg.getUserId(),formCReg.getMobile(),formCReg.getMobile_otp());
			boolean validEmailOtp=fromCRegRepo.validateEmailOtp(formCReg.getUserId(),formCReg.getEmailId(),formCReg.getEmail_otp());
		    
			if(!validMobileOtp) {
				throw new ValidationException("Please Enter valid Mobile otp.");
			}
			if(!validEmailOtp) {
				throw new ValidationException("Please Enter valid Email otp.");
			}
			if(validMobileOtp && validEmailOtp) {
				resObj=fromCRegRepo.submitRegDetails(formCReg);
				resultMap.put("userId",resObj.getUserId() );
				resultMap.put("acco_code",resObj.getAcco_code());
			}

		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
	}

	
	 @GetMapping("/masters/formc/frro-list/{state}/{cityDist}") 
	 public ResponseEntity<?>  fetchFrroListList(@PathVariable String state,@PathVariable String cityDist){
		 List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		  res= fromCRegRepo.frroList(state, cityDist); 
		  return new ResponseEntity<List<Map<String,Object>>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	

}
	

