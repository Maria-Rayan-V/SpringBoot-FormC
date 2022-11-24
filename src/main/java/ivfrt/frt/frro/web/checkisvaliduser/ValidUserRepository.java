package ivfrt.frt.frro.web.checkisvaliduser;

public interface ValidUserRepository {

	public ValidUser validUser(String username) throws Exception;
	
	public ValidUser validUser_FromEservice(String username) throws Exception;
	public void deleteSaltData_fr_users_salt(String userid) ;
	public String fetchCaptcha(String userid);
	public String refreshCaptcha(String userid)throws Exception;
}
