package ivfrt.frt.frro.web.user.forgotpassword;

public class ForgotPassword {

	private String userEmail;
	private String clientIp;
	private String clientCaptcha;
	
	
	
	public ForgotPassword(String userEmail, String clientIp, String clientCaptcha) {
		super();
		this.userEmail = userEmail;
		this.clientIp = clientIp;
		this.clientCaptcha = clientCaptcha;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getClientCaptcha() {
		return clientCaptcha;
	}
	public void setClientCaptcha(String clientCaptcha) {
		this.clientCaptcha = clientCaptcha;
	}
	
	
	
}
