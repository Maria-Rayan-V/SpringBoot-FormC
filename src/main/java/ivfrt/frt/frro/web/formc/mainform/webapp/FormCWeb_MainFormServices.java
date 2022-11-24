package ivfrt.frt.frro.web.formc.mainform.webapp;

import java.util.Date;
import java.util.Random;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FormCWeb_MainFormServices implements FormCWeb_MainFormRepository {

	@Autowired
	@Qualifier("jdbcFormC")
	private JdbcTemplate jdbcFormC;
	
	
	
	@Override
	public FormCWeb_GenAppID genrateAppiD(@Valid FormCWeb_GenAppID reqParamObj) throws Exception {

		
		validateAccoCodeAndFrroCode(reqParamObj.getAcco_code(), reqParamObj.getFrro_fro_code(),reqParamObj.getEntered_by());
		
		int status = 0;
		if (reqParamObj.getForm_c_appl_id() != null) {
			status = update_DataFormC(reqParamObj);
			reqParamObj.setMessage("Records are Updated successfully.");
		} else {
			String form_c_appl_id = genFormCAppId();
			reqParamObj.setForm_c_appl_id(form_c_appl_id);
			if (reqParamObj.getEntered_on() == null) {
				reqParamObj.setEntered_on(new Date());
			}
			status = insertData_fr_formc_trans_temp(reqParamObj);
			reqParamObj.setMessage("Registred successfully with Application id :" + form_c_appl_id);
		}

		if (status > 0) {
			return reqParamObj;
		} else {
			throw new Exception("Some unexpected issues please resubmit.");
		}
	}

	private String genFormCAppId() {
		int ctrl = 1;
		String tempAppId = "";
		do {
			String datePart = returnDtepart();
			String alfaString = randGenerator(6);
			tempAppId = datePart + alfaString;

			int existInTemp = checkExistanceInTempTable(tempAppId);
			int existInMain = checkExistanceInMainTable(tempAppId);

			if (existInTemp == 0 && existInMain == 0) {
				ctrl = 0;
			}

		} while (ctrl > 0);

		return tempAppId;
	}
	private int checkExistanceInTempTable(String tempAppId) {

		String query = "select count(*) from fr_formc_trans_temp where form_c_appl_id=?";
		return jdbcFormC.queryForObject(query, new Object[] { tempAppId }, Integer.class);

	}

	private int checkExistanceInMainTable(String tempAppId) {

		String query = "select count(*) from fr_formc_trans where form_c_appl_id=?";
		return jdbcFormC.queryForObject(query, new Object[] { tempAppId }, Integer.class);

	}

	private String returnDtepart() {
		String query = "select to_char(localtimestamp,'DDMMYY')";
		return jdbcFormC.queryForObject(query, String.class);
	}

	private String randGenerator(int range) {
		String randString = "";
		if (range <= 0) {
			return randString;
		}
		StringBuffer sb = new StringBuffer();
		String block = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // add "GH" in block
		sb.append(block).append("0123456789");
		block = sb.toString();
		sb = new StringBuffer();
		Random random = new Random();

		try {
			for (int i = 0; i < range; i++) {
				sb.append(Character.toString(block.charAt(random.nextInt(block.length() - 1))));
			}
			randString = sb.toString();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException :");
			System.out.println("of formc.utility -> getrandGenerator() ");
			// e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException :");
			System.out.println("of formc.utility -> getrandGenerator() ");
			// e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception :" + e);
			System.out.println("in formc.utility -> getrandGenerator() ");
			// e.printStackTrace();
		}
		return randString;
	}
	private int insertData_fr_formc_trans_temp(@Valid FormCWeb_GenAppID reqParamObj) {
		String query = "INSERT INTO fr_formc_trans_temp"
				+ " ( form_c_appl_id, acco_code,gender,nationality, "
				+ " frro_fro_code,entered_by,entered_on,given_name,surname,passport_no) "
				+ " VALUES ( ?, ?, ?, ?,  ?, ?, now(),?,?,? ) ";

		return jdbcFormC.update(query, reqParamObj.getForm_c_appl_id(), reqParamObj.getAcco_code(),
				reqParamObj.getGender(), 
				reqParamObj.getNationality(), reqParamObj.getFrro_fro_code(), reqParamObj.getEntered_by(),
				reqParamObj.getGiven_name(), reqParamObj.getSurname(),reqParamObj.getPassnum());
	
	}

	private int update_DataFormC(@Valid FormCWeb_GenAppID reqParamObj) {


		String query = "UPDATE fr_formc_trans_temp set acco_code=?,gender=?,nationality=?, "
				+ " frro_fro_code=?,entered_by=?,entered_on=now(),given_name=?,surname=?,passport_no=? "
				+ " where form_c_appl_id=? ";

		int status = jdbcFormC.update(query, reqParamObj.getAcco_code(), reqParamObj.getGender(),
				 reqParamObj.getNationality(),
				 reqParamObj.getFrro_fro_code(), reqParamObj.getEntered_by(),
				 reqParamObj.getGiven_name(), reqParamObj.getSurname(),reqParamObj.getPassnum(), reqParamObj.getForm_c_appl_id());

		return status;

	
	}

	private void validateAccoCodeAndFrroCode(String accoCode, String frroCode, String userid) {
		
		String q1 = "select b.acco_code from fr_users a,fr_acco_reg_formc b where a.userid=b.userid and a.userid=? limit 1";
		String r1 = jdbcFormC.queryForObject(q1, new Object[] {userid}, String.class);
		
		if(!r1.equals(accoCode)) {
			throw new ValidationException("Invalid Accomodation code.");
		}
		
		String q2 = "select a.frro_fro_code from fr_users a,fr_acco_reg_formc b where a.userid=b.userid and a.userid=? limit 1";
		String r2 = jdbcFormC.queryForObject(q2, new Object[] { userid }, String.class);
		if(!r2.equals(frroCode)) {
			throw new ValidationException("Invalid FRRO code.");
		}
	}

	@Override
	public String checkForValidToken(String token, String user) {
		
		String q2 = "select count(*) from invalid_token_list where userid=? and jwt=?";
		int res = jdbcFormC.queryForObject(q2, new Object[] { user,token }, Integer.class);
		if(res>0) {
		   return "INVALID";
		}
		return "VALID";
	}
	
	@Override
	public String fetchUserId(String applicationId) throws Exception {

		String result = "";

		try {
			String query = "select entered_by from fr_formc_trans_temp where form_c_appl_id=?";
			result = jdbcFormC.queryForObject(query, new Object[] { applicationId }, String.class);
			System.out.println("-------------USER-----------------" + result);
		} catch (Exception e) {
			throw new ValidationException("Invalid Application Id. Can't proceed.");
		}

		return result;

	}

	@Override
	public int deletePendingApplication(
			@NotBlank @Pattern(regexp = "(^[0-9a-zA-Z]{12})", message = "Invalid Application Id.") String applicationid) throws Exception {
		try {
		String query = "delete from fr_formc_trans_temp where form_c_appl_id=?";
		 jdbcFormC.update(query, applicationid );
		}catch (Exception e) {
			throw new Exception("Somthing went wrong.");
		}
		return 1;
	}
	
	
}
