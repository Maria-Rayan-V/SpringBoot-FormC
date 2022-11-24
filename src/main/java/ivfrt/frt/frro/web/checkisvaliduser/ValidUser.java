package ivfrt.frt.frro.web.checkisvaliduser;

import javax.validation.constraints.Email;

public class ValidUser {

	@Email
	private String userId;
	private String salt;
	private String status;
	private String captcha;
	
	
	public ValidUser(String userId, String username, String status) {
		super();
		this.userId = userId;
		this.salt = username;
		this.status = status;
	}
	public ValidUser() {
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	
	
	
}
