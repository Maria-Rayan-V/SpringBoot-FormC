package ivfrt.frt.frro.web.user.forgotpassword;

public interface ChangePasswordRepositry {

	String changePassword(ChangePassword changePassword, String user) throws Throwable;
	String changePasswordEServices(ChangePassword changePassword,String user) throws Throwable;
	public String checkForValidToken(String token,String userid) throws Exception;
}
