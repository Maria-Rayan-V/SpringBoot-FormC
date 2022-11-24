package ivfrt.frt.frro.web.jwt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import utilities.RandomSaltGenerator;

@Repository
public class TestUserListDao implements TestRepositry {

	/*
	 * @Autowired JdbcTemplate jdbc;
	 */

	@Autowired
	@Qualifier("jdbcFormC")
	private JdbcTemplate jdbcFormc;

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbcEservices;

	@Autowired
	private PasswordEncoder bCencoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.http.request.header}")
	private String tokenHeader;
	// String username, String password,String designation,String useremailId,
	// String passportNo,String mobileNo,String lastLogin

	public List<JwtUserDetails> findUserByName(String username, String salt, boolean isLoginRequest) {
		RandomSaltGenerator ranUtl = new RandomSaltGenerator();
		String saltEnc = ranUtl.encryptSHA256(salt);
		// String query="select
		// given_name,password,emailid,passportnumber,mobilenumber,entered_on from
		// e_users WHERE emailid =?";
		String query = "select given_name,surname, password, emailid, passportnumber, mobilenumber, b.login_date from e_users a, elogin_history b where a.emailid = b.login_by and LOWER(a.emailid) = ?  order by b.login_date desc   limit 1";
		List<JwtUserDetails> jwt = new ArrayList<JwtUserDetails>();
		jwt = jdbcEservices.query(query, new Object[] { username.toLowerCase() },
				(rs, rowNum) -> new JwtUserDetails(rs.getString("given_name") + " " + rs.getString("surname"),
						bCencoder.encode(ranUtl.encryptSHA256(rs.getString("password") + saltEnc)),
						rs.getString("emailid"), rs.getString("passportnumber"), rs.getString("mobilenumber"),
						rs.getString("login_date"),"","","","","","","","",""

				));

		inset_Elogin_history(username, "mapp");
		return jwt;
	}

	@Override
	public List<JwtUserDetails> findUserByNameFormc(String username, String salt, boolean isLoginRequest) {
		RandomSaltGenerator ranUtl = new RandomSaltGenerator();

		String saltEnc = ranUtl.encryptSHA256(salt);
		// System.out.println("-----------"+saltEnc);

		List<JwtUserDetails> jwt = new ArrayList<JwtUserDetails>();
		FormC_FR_UserDetails userBasic=new FormC_FR_UserDetails();
		FormC_AccomDetails accomDtl=new FormC_AccomDetails();
		String lastLogin = fetchLastLogin(username);
		
		
	     
		 String u_q="select a.userid,a.password,a.e_mail_id,a.phone_no,a.inactive_date,a.frro_fro_code,a.active from fr_users a where a.userid=? and a.user_type_code='5'";
		
		 userBasic=jdbcFormc.queryForObject(u_q, new Object[] { username },
					(rs, rowNum) ->new FormC_FR_UserDetails(rs.getString("userid"),rs.getString("password"),rs.getString("e_mail_id"),rs.getString("phone_no"),
							rs.getString("inactive_date"),rs.getString("frro_fro_code"),rs.getString("active")));
		 
		 
		 if(userBasic.getActive().equals("Y")) {
			 
			 String q_accom=" select b.acco_code,c.state_code,c.city_district_code,acom.acco_name,acom.acco_address, "
			 		+ "(select  city_district_name   from city_district where  city_district_code=acom.city_district and state_code=acom.state_code)  as accom_city_district_name, "
			 		+ " (select state_name from fr_states where  state_code=acom.state_code) as accom_state, "
			 		+ " c.frro_fro_desc as frroDesc,c.address as frro_fro_adr, "
			 		+ " (select  city_district_name   from city_district where  city_district_code=c.city_district_code and state_code=c.state_code) as frro_fro_city_district_name, "
			 		+ " (select state_name from fr_states where  state_code=c.state_code) as frro_fro_state,acom.frro_fro_code  "
			 		+ " from fr_acco_reg_formc b,fr_frro_fro_list c ,fr_accomodator acom where userid=?  "
			 		+ " and c.frro_fro_code=?  and acom.acco_code=b.acco_code  and acom.frro_fro_code=c.frro_fro_code ";
			 
			 
			
			 accomDtl=jdbcFormc.queryForObject(q_accom, new Object [] {username,userBasic.getFrro_fro_code()},
					 (rs, rowNum) -> new FormC_AccomDetails(lastLogin, rs.getString("acco_code"), rs.getString("frro_fro_code"), rs.getString("state_code"),
							 rs.getString("city_district_code"),rs.getString("acco_name"),rs.getString("frroDesc"),rs.getString("acco_address"),rs.getString("accom_city_district_name"),
							 rs.getString("accom_state"), rs.getString("frro_fro_adr"),  rs.getString("frro_fro_city_district_name"), rs.getString("frro_fro_state")));
			 
			 
		 }
		 jwt.add(new JwtUserDetails(userBasic.getUserid(),
						bCencoder.encode(ranUtl.encryptSHA256(userBasic.getPassword() + saltEnc)), "",
						userBasic.getE_mail_id(), "", userBasic.getPhone_no(), lastLogin, accomDtl.getAcco_code(),
						userBasic.getFrro_fro_code(), accomDtl.getState_code(), accomDtl.getCity_district_code(),
						accomDtl.getAcco_name(), accomDtl.getFrroDesc(), accomDtl.getAcco_address(),
						accomDtl.getAccom_city_district_name(), accomDtl.getAccom_state(),
						accomDtl.getFrro_fro_adr(), accomDtl.getFrro_fro_city_district_name(),
						accomDtl.getFrro_fro_state(),userBasic.getActive()));
		 

		insetLoginHistory(username, "mapp"); // need Ip address
		// System.out.println(jwt.get(0).getPassword());
		return jwt;
	}

	private String fetchLastLogin(String username) {
		String a = "";
		try {
			String query = "select login_date from fr_login_history where login_by=?  order by login_date desc limit 1";
			a = jdbcFormc.queryForObject(query, new Object[] { username.toLowerCase() }, String.class);
		} catch (Exception e) {

		}
		return a;
	}

	private void insetLoginHistory(String user, String from_the_client) {

		String query = "insert into fr_login_history(login_by,login_date,from_the_client) values(?,clock_timestamp(),?)";
		jdbcFormc.update(query, user.toLowerCase(), from_the_client);
		return;

	}

	private void inset_Elogin_history(String user, String from_the_client) {

		String query = "insert into elogin_history(login_by,login_date,from_the_client) values(?,clock_timestamp(),?)";
		jdbcEservices.update(query, user.toLowerCase(), from_the_client);
		return;

	}

	@Override
	public boolean checkUserFrom(String username) {

		String query = "";
		return jdbcEservices.queryForObject(query, Boolean.class);

	}

	@Override
	public String fetchSalt(String userid) {
		int isAvl = isAvlSalt(userid);
		if (isAvl > 0) {
			String query = "select salt from fr_users_salt where userid=?";
			return (String) jdbcEservices.queryForObject(query, new Object[] { userid }, String.class);
		} else {
			return "";
		}

	}

	private int isAvlSalt(String userid) {

		String query = "select count(*) from fr_users_salt where userid=?";
		return jdbcEservices.queryForObject(query, new Object[] { userid }, Integer.class);

	}

	@Override
	public String logOut(HttpServletRequest req) {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);

		String updateQuery = "update fr_login_history set logout_date=clock_timestamp() " + " where login_by=? and "
				+ " login_date =(select login_date from fr_login_history where login_by=? and logout_date is null  order by login_date desc limit 1 )";
		jdbcFormc.update(updateQuery, user, user);
		return "LOGOUT";
	}
}
