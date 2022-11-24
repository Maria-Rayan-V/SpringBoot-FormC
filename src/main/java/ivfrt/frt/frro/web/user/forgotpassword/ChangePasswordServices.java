package ivfrt.frt.frro.web.user.forgotpassword;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.checkisvaliduser.ValidUser;
import utilities.RandomSaltGenerator;

@Repository
public class ChangePasswordServices implements ChangePasswordRepositry {

	private String existUser="Y";
	
	 @Autowired
	 @Qualifier("jdbcFormC")
	 private JdbcTemplate jdbcFormc;
	 
	 @Autowired
	 @Qualifier("jdbcEservices")
	 private JdbcTemplate jdbcEservices;
	
	
	@Autowired
	private  PasswordEncoder bCencoder;
	
	@Override
	public String changePassword(ChangePassword changePassword, String user) throws Throwable {
		// TODO Auto-generated method stub
		int updateStatus=0; String updatemsg="PASSWORD_NOT_CHANGE";
		String newpass=changePassword.getNewpassword();
		String conformpass=changePassword.getConfirmpassword();
		
		
		if(newpass.equals(conformpass)) {
		  
			
			boolean status=checkUserExist(changePassword,user);
			
			if(status) {
			
				List<String> oldPassHistoryList=new ArrayList<String>();
				
				String queryx1 = "select DISTINCT foo.password,foo.modified_on from "
						+ " (select a.* from frh_users a where userid = ? order by modified_on desc ) AS foo "
						+ " order by modified_on desc LIMIT 3";
				
				//oldPassHistoryList= jdbc.queryForList(queryx1, new Object[] {user}, String.class);
				oldPassHistoryList=jdbcFormc.query(queryx1, new Object[] {user}, new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("password");
					}
					
				});
				
				for (String lastPass : oldPassHistoryList) {
					if(lastPass.equals(encPassword(changePassword.getNewpassword()))){
						//updatemsg="MATCHED_LAST_3_PASS";
						throw new ValidationException("New Password should not be same from last 3 Password.");
						}
					
					}
					/*
					 * if(!updatemsg.equals("MATCHED_LAST_3_PASS")) {
					 * updateStatus=updatePassword(changePassword,user); }
					 */
				 updateStatus=updatePassword(changePassword,user);
			} else if(existUser.equals("N")) {
				//updatemsg="INVALID_USER";
				throw new ValidationException("Old password is not currect.");
			}
			
			
		}else {
			//updatemsg="NEW_AND_CNF_PASS_NOT_SAME";
			throw new ValidationException("New Password and Conform Password must be same");
		}
		
		
		if(updateStatus>0) {
			//updaateFRH_USERS(changePassword.getNewpassword(),user);
			updatemsg="PASSWORD_CHANGE";
		}
		return updatemsg;
	}



	



	private void updaateFRH_USERS(String newpassword, String user,String frroCode) {

		String nquery = "Update frh_users SET password=?,password_updated_on=clock_timestamp()"
				+ "  where userid=? and active ='Y'  and frro_fro_code=?";
		jdbcFormc.update(nquery,newpassword,user,frroCode);
		 
	
		
	}



	private boolean checkUserExist(ChangePassword changePassword, String user) throws Exception {
		int exist=0;
		String queryChk="select count(*) from fr_users where userid=? and active = 'Y' and frro_fro_code=? and password=? ";
		 exist=jdbcFormc.queryForObject(queryChk,new Object[] {user,changePassword.getFrro_fro_code(),changePassword.getPassword()}, Integer.class);
		
		if(exist==0) {
			existUser="N";
			return false;
		}else {
			return true;
		}
		
		
	}

	private int updatePassword(ChangePassword changePassword, String user) {
		
		
		updaateFRH_USERS(encPassword(changePassword.getNewpassword()),user,changePassword.getFrro_fro_code());
		
		String nquery = "Update fr_users SET password=?,password_updated_on=clock_timestamp()"
				+ "  where userid=? and active ='Y'  and frro_fro_code=?";
		return jdbcFormc.update(nquery,encPassword(changePassword.getNewpassword()),user,changePassword.getFrro_fro_code());
		 
	}

	String encPassword(String pass) {
		RandomSaltGenerator ranUtl = new RandomSaltGenerator();
		String md5Newpass=ranUtl.getMd5(pass);
		String sha256OFMd5_NewPass=ranUtl.encryptSHA256(md5Newpass);
		return sha256OFMd5_NewPass;
	}
	private int updatePasswordEservices(ChangePassword changePassword, String user) {
		String nquery = "Update e_users SET password=?, password_update_on=clock_timestamp() where emailid=? and active='Y'";
		return jdbcEservices.update(nquery,bCencoder.encode(changePassword.getNewpassword()),user);
		 
	}

	@Override
	public String changePasswordEServices(ChangePassword changePassword, String user) throws Exception {
		// TODO Auto-generated method stub
		int updateStatus=0; String updatemsg="PASSWORD_NOT_CHANGE";
		String newpass=changePassword.getNewpassword();
		String conformpass=changePassword.getConfirmpassword();
		if(newpass.equals(conformpass)) {
		
			boolean status=checkUserExistEservices(changePassword, user);
			
			if(status) {
			
				List<String> oldPassHistoryList=new ArrayList<String>();
				List<ChangePassword> tempObj=new ArrayList<ChangePassword>();
				String queryx1 = "select password,password2,password3 from e_users where emailid=? and active='Y'";
				
				//oldPassHistoryList= jdbc.queryForList(queryx1, new Object[] {user}, String.class);
				tempObj= jdbcEservices.query(queryx1,new Object[] {user}, 
						(rs,rowNum)-> new ChangePassword(	
		        		   rs.getString("password"),
		        		   rs.getString("password2"),
		        		   rs.getString("password3") )
				);
		           
				oldPassHistoryList.add(tempObj.get(0).getPassword());
				if(tempObj.get(0).getNewpassword()!=null)
				oldPassHistoryList.add(tempObj.get(0).getNewpassword());
				if(tempObj.get(0).getConfirmpassword()!=null)
				oldPassHistoryList.add(tempObj.get(0).getConfirmpassword());
				
				
				for (String lastPass : oldPassHistoryList) {
					if(lastPass.equals(changePassword.getNewpassword())){
						updatemsg="MATCHED_LAST_3_PASS";
						//throw new Exception("New Password should not be same from last 3 Password.");
					}
					else if(!updatemsg.equals("MATCHED_LAST_3_PASS")) {
						updateStatus=updatePasswordEservices(changePassword,user);	
					}
				}
				
			} else if(existUser.equals("N")) {
				updatemsg="INVALID_USER OR WRONG CURRENT PASSWORD";
			}
			
			
		}else {
			updatemsg="NEW_AND_CNF_PASS_NOT_SAME";
			//throw new Exception("New Password and Conform Password must be same");
		}
		
		
		if(updateStatus>0) {
			//updaateFRH_USERS(changePassword.getNewpassword(),user);
			updatemsg="PASSWORD_CHANGE";
		}
		return updatemsg;
	}
	
	private boolean checkUserExistEservices(ChangePassword changePassword, String user) throws Exception {
		int exist=0;
		String oldPass=changePassword.getPassword();
		String queryChk="select count(*) from e_users where emailid=? and active = 'Y' ";
		 exist=jdbcEservices.queryForObject(queryChk,new Object[] {user}, Integer.class);
		
		if(exist==0) {
			existUser="N";
			return false;
		}
		String query="select password from e_users where emailid=? and active = 'Y' ";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String dbPass=jdbcEservices.queryForObject(query,new Object[] {user}, String.class);
		System.out.println(dbPass);
		System.out.println("========="+encoder.matches(oldPass, dbPass));
		//if(dbPass.equals(oldPass)) {
		if((encoder.matches(oldPass, dbPass))==true){
			return true;	
		}
		else {
			return false;
		}
	}
	
	  public static String MD5(String s) throws Exception {
	      MessageDigest m=MessageDigest.getInstance("MD5");
	      m.update(s.getBytes(),0,s.length());     
	      return new BigInteger(1,m.digest()).toString(16); 
	   }


	  @Override
		public String checkForValidToken(String token, String userid) throws Exception {
			
			String q2 = "select count(*) from invalid_token_list where userid=? and jwt=?";
			int res = jdbcFormc.queryForObject(q2, new Object[] { userid,token }, Integer.class);
			if(res>0) {
			   return "INVALID";
			}
			
			return "VALID";
		}

	

}
