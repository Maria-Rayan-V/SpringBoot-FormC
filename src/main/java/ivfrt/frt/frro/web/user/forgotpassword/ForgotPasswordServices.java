package ivfrt.frt.frro.web.user.forgotpassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import utilities.RandomSaltGenerator;

@Repository
public class ForgotPasswordServices implements ForgotPasswordRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbc;
	boolean checkpasswordforchange=false;
	
	@Override
	public String forgotPasswordLink(ForgotPassword forgotPassword) throws Throwable {
		
		checkpasswordforchange=checkemailforforgotpassword(forgotPassword);
		
		if(checkpasswordforchange) {
			String pascreate = null;
			String pwdgen = null;
			pascreate = RandomSaltGenerator.generateSalt();
			
			//pwdgen=
			//pwdgen=B.encrypt(pascreate);
			
			
		}
		
		return null;
	}
	
	
	
	
	public boolean checkemailforforgotpassword(ForgotPassword forgotPassword) throws Exception 
	{
	
		
		String query  = "select count(emailid) as userstatus from  e_users   where lower(emailid)=lower(?)";
			return jdbc.queryForObject(query, Boolean.class);
	
	}
}
