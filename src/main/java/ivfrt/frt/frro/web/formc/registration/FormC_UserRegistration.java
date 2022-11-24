package ivfrt.frt.frro.web.formc.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FormC_UserRegistration {

    @NotBlank(message = "User ID cannot be Blank")
    @Size(min = 8, message = "User ID cannot be less than 8 Character")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "User ID must have minimum one alphabetic character and minimum one numeric character")
    private String userId;
    
    @NotBlank(message = "Password cannot be Empty")
    //@Pattern(regexp = ConstantUtil.PASSWORD,message = "The Password isn't Strong enough \\n Tips : Password should be atleast of length 8, should be a combination of Alphanumeric (Upper case and Lower case Both) and Special Characters")
    private String password;
    @NotBlank(message = "Confirm Password cannot be Empty")
    private String confirmPassword;
   
    @NotBlank(message = "Please Enter Your Name")
    private String userName;
    
    @NotBlank
    @Email(message = "Invalid Email id.")
    private String emailId;
    @NotBlank
    @Size(max = 10,min = 10,message = "Mobile Number must be 10 digit") 
    private String mobile;
    @NotBlank
    @Size(max = 4,min = 4,message = "Mobile OTP must be 4 digit") 
    private String mobile_otp;
    @NotBlank
    @Size(max = 4,min = 4,message = "Email OTP must be 4 digit") 
    private String email_otp;
    @NotBlank
    private String captcha;
    
    private String clientIp;
    
    private String isMobileVerified;
    private String isEmailVerified;
    
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile_otp() {
		return mobile_otp;
	}
	public void setMobile_otp(String mobile_otp) {
		this.mobile_otp = mobile_otp;
	}
	public String getEmail_otp() {
		return email_otp;
	}
	public void setEmail_otp(String email_otp) {
		this.email_otp = email_otp;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getIsMobileVerified() {
		return isMobileVerified;
	}
	public void setIsMobileVerified(String isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}
	public String getIsEmailVerified() {
		return isEmailVerified;
	}
	public void setIsEmailVerified(String isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
    
    
    
    
    
    
    
}
