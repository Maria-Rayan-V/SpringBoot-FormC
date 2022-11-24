package ivfrt.frt.frro.web.formc.registration;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class FormC_Registration {

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
	     private String name;
	     @NotBlank(message = "Please Choose Your Gender")
	     private String gender;
	     @NotBlank(message = "Please Enter Your Date of Birth")
	     @Pattern(regexp = ConstantUtil.DATE_DDMMYYYY,message = "Date of Birth :Invalid date format")
	     private String dob;
	     @NotBlank(message = "Please Enter Your Designation")
	     private String designation;
	     @NotBlank(message = "Please Enter Your E-Mail ID")
	     private String emailId;
	     private String mobile;
	     private String mobile_otp;
	     private String email_otp;
	     private String phnNumber;
	     @NotBlank(message = "Please Choose your Nationality")
	     @Size(max=3,message = "Invalid Nationality")
	     private String nationality;
	     @NotBlank(message = "Please Enter Accomodator Name")
	     private String accomName;
	     @NotBlank(message = "Please Enter Accomodator's Capacity")
	     @Pattern(regexp = ConstantUtil.NUM,message="Only Numeric Value accepted.")
	     private String accomCapacity;
	     @NotBlank(message = "Please Enter Accomodator Address")
	     private String accomAddress;
	     @NotBlank(message = "Please Choose Accomodator State")
	     private String accomState;
	     @NotBlank(message = "Please Choose Accomodator City/District")
	     private String accomCityDist;
	     @NotBlank(message = "FRRO Description is required.")
	     @Size(min=4,max=4,message = "Invalid FRRO Description")
	     private String frroTypeCode;  //FRRO Description
	     @NotBlank(message = "Please Choose Accomodator Type")
	     private String accomodationType;
	     @NotBlank(message = "Please Choose Accomodator Grade")
	     private String accomodationGrade;
	     
	     private String accomEmail;
	     private String accomMobile;
	     private String accomPhoneNum;
	     private String clientIp;
	     @Size(min = 1,message = "Minimum One Owner Should be Specified")
	     private List<OwnerDetails> ownerDetails;
		 private String captcha;
	     private String acco_code;
		 
		 
		 
		 
		 
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
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
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
		public String getPhnNumber() {
			return phnNumber;
		}
		public void setPhnNumber(String phnNumber) {
			this.phnNumber = phnNumber;
		}
		public String getNationality() {
			return nationality;
		}
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		public String getAccomodationType() {
			return accomodationType;
		}
		public void setAccomodationType(String accomodationType) {
			this.accomodationType = accomodationType;
		}
		public String getAccomodationGrade() {
			return accomodationGrade;
		}
		public void setAccomodationGrade(String accomodationGrade) {
			this.accomodationGrade = accomodationGrade;
		}
		
	
		public List<OwnerDetails> getOwnerDetails() {
			return ownerDetails;
		}
		public void setOwnerDetails(List<OwnerDetails> ownerDetails) {
			this.ownerDetails = ownerDetails;
		}
		public String getCaptcha() {
			return captcha;
		}
		public void setCaptcha(String captcha) {
			this.captcha = captcha;
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
		public String getAccomName() {
			return accomName;
		}
		public void setAccomName(String accomName) {
			this.accomName = accomName;
		}
		public String getAccomCapacity() {
			return accomCapacity;
		}
		public void setAccomCapacity(String accomCapacity) {
			this.accomCapacity = accomCapacity;
		}
		public String getAccomAddress() {
			return accomAddress;
		}
		public void setAccomAddress(String accomAddress) {
			this.accomAddress = accomAddress;
		}
		public String getAccomState() {
			return accomState;
		}
		public void setAccomState(String accomState) {
			this.accomState = accomState;
		}
		public String getAccomCityDist() {
			return accomCityDist;
		}
		public void setAccomCityDist(String accomCityDist) {
			this.accomCityDist = accomCityDist;
		}
	
		
		public String getFrroTypeCode() {
			return frroTypeCode;
		}
		public void setFrroTypeCode(String frroTypeCode) {
			this.frroTypeCode = frroTypeCode;
		}
		public String getAccomEmail() {
			return accomEmail;
		}
		public void setAccomEmail(String accomEmail) {
			this.accomEmail = accomEmail;
		}
		public String getAccomMobile() {
			return accomMobile;
		}
		public void setAccomMobile(String accomMobile) {
			this.accomMobile = accomMobile;
		}
		public String getAccomPhoneNum() {
			return accomPhoneNum;
		}
		public void setAccomPhoneNum(String accomPhoneNum) {
			this.accomPhoneNum = accomPhoneNum;
		}
		public String getClientIp() {
			return clientIp;
		}
		public void setClientIp(String clientIp) {
			this.clientIp = clientIp;
		}
		public String getAcco_code() {
			return acco_code;
		}
		public void setAcco_code(String acco_code) {
			this.acco_code = acco_code;
		}
		
	
}
