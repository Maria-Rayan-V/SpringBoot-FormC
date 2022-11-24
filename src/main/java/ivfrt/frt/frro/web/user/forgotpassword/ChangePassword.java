package ivfrt.frt.frro.web.user.forgotpassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class ChangePassword {

	private String password;
	@Size(max=20,min=8, message = "Password is minimum 8 characters,use at least one special character and One UpperCase and One Lower case.")
	@Pattern(regexp = ConstantUtil.PASSWORD, message = "Password have minimum eight characters, at least one letter, one number and one special character")
	@NotBlank(message = "New Password is required.")
	private String newpassword;
	@NotBlank(message = "Confirm Password is required.")
	private String confirmpassword;
	private String frro_fro_code;
	
	
	public ChangePassword() {
		super();
	}
	public ChangePassword(String password, String newpassword, String confirmpassword) {
		super();
		this.password = password;
		this.newpassword = newpassword;
		this.confirmpassword = confirmpassword;
		}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getFrro_fro_code() {
		return frro_fro_code;
	}
	public void setFrro_fro_code(String frro_fro_code) {
		this.frro_fro_code = frro_fro_code;
	}
		

}
