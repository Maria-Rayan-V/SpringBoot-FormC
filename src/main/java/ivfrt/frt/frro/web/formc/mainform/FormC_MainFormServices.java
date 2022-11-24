package ivfrt.frt.frro.web.formc.mainform;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FormC_MainFormServices implements FormC_MainFormRepository {

	@Autowired
	@Qualifier("jdbcFormC")
	private JdbcTemplate jdbcFormC;

	@Override
	public FormC_GenAppID genrateAppiD(FormC_GenAppID reqParamObj) throws Exception {

		
		validateAccoCodeAndFrroCode(reqParamObj.getAcco_code(), reqParamObj.getFrro_fro_code(),reqParamObj.getEntered_by());
		/*
		 * In Case of Edit Application FormC_GenAppID object have not null
		 * form_c_appl_id. if form_c_appl_id is not null then Update details Otherwise
		 * generate new Application id and insert details.
		 */
		
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

	private int update_DataFormC(FormC_GenAppID reqParamObj) {

		String query = "UPDATE fr_formc_trans_temp set acco_code=?,gender=?,city_district_of_reference_in_india=?,state_of_reference_in_india=?,"
				+ " country_outside_india=?,frro_fro_code=?,entered_by=?,entered_on=now(),given_name=?,surname=? where form_c_appl_id=? ";

		int status = jdbcFormC.update(query, reqParamObj.getAcco_code(), reqParamObj.getGender(),
				reqParamObj.getCityofrefinind(), reqParamObj.getStateofrefinind(),
				reqParamObj.getCountry_outside_india(), reqParamObj.getFrro_fro_code(), reqParamObj.getEntered_by(),
				reqParamObj.getGiven_name(), reqParamObj.getSurname(), reqParamObj.getForm_c_appl_id());

		return status;

	}

	private int insertData_fr_formc_trans_temp(FormC_GenAppID reqParamObj) {

		
		String query = "INSERT INTO fr_formc_trans_temp"
				+ " ( form_c_appl_id, acco_code,gender,city_district_of_reference_in_india, "
				+ " state_of_reference_in_india,country_outside_india,frro_fro_code,entered_by,entered_on,given_name,surname) "
				+ " VALUES ( ?, ?, ?, ?, ?, ?,  ?, ?, now(),?,? ) ";

		return jdbcFormC.update(query, reqParamObj.getForm_c_appl_id(), reqParamObj.getAcco_code(),
				reqParamObj.getGender(), reqParamObj.getCityofrefinind(), reqParamObj.getStateofrefinind(),
				reqParamObj.getCountry_outside_india(), reqParamObj.getFrro_fro_code(), reqParamObj.getEntered_by(),
				reqParamObj.getGiven_name(), reqParamObj.getSurname());
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

	public java.sql.Date str_to_date_formc(String data) {
		java.sql.Date DOBdate_sql = null;

		if ((data == "") || (data == "null") || (data == null)) {
			return DOBdate_sql;
		}
		try {
			DateFormat formatter = null;
			formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
			java.util.Date DOBdate_j = null;
			DOBdate_j = formatter.parse(data);
			DOBdate_sql = new java.sql.Date(DOBdate_j.getTime());
		} catch (Exception e) {
			System.out.println("Exception in Registration src/frro.Utility.java -> str_to_date_eservice() ");
			// e.printStackTrace();
			return null;
		}
		return DOBdate_sql;
	}

	@Override
	public FormC_PersonalDetail submit_FormC_PersonalDetail(FormC_PersonalDetail persionalDetails) throws Exception {

		String query = "UPDATE fr_formc_trans_temp set  surname=?, given_name=?, date_of_birth_format=?, date_of_birth=?, "
				+ " address_outside_india=?, city_outside_india=?, country_outside_india=?,nationality=?,spl_category_code=?  where form_c_appl_id=?";
		 
		int status = jdbcFormC.update(query, persionalDetails.getSurname(), persionalDetails.getName(),
				persionalDetails.getDobformat(), str_to_date_formc(persionalDetails.getDob()),
				persionalDetails.getAddroutind(), persionalDetails.getCityoutind(), persionalDetails.getCounoutind(),
				persionalDetails.getNationality(),persionalDetails.getSplcategorycode(), persionalDetails.getForm_c_appl_id());

		if (status > 0) {
			return persionalDetails;
		} else {
			throw new Exception("Somthing went wrong");
		}
	}

	@Override
	public FormC_PassPortVisa submit_FormC_PassPortVisa(FormC_PassPortVisa passvisa) throws Exception {

		
		
		   validatePassPortVisaDetailsBySpclCategory(passvisa);
		
		        
		
			if(passvisa.getPassdate()!=null) {
				dateCompare(passvisa.getPassexpdate(),passvisa.getPassdate(),"Passport Expiry Date can not be lesser than Passport Date of issue.");
			}
			if((passvisa.getVisadate()!=null) && (passvisa.getVisaexpdate()!=null))
			dateCompare(passvisa.getVisaexpdate(),passvisa.getVisadate(),"Visa Expiry Date can not be lesser than Visa Date of issue.");
			
			if((passvisa.getVisadate()!=null) && (passvisa.getVisaexpdate()!=null))
			dateCompare(passvisa.getVisadate(),passvisa.getPassdate(),"visadate can not be lesser than passdate.");
			
			
		    
		
		
		
		
		String query = "UPDATE fr_formc_trans_temp set  passport_no=?, passport_place_of_issue=? ,  passport_date_of_issue=?, "
				+ " passport_expiry_date=? , passport_issue_country=? , "
				+ " visa_no=?, visa_date_of_issue=? , visa_expiry_date=?, visa_type=? , visa_place_of_issue=?,visa_subtype_code=?,"
				+ " visa_issue_country=?  where form_c_appl_id=?";

		int status = jdbcFormC.update(query, passvisa.getPassnum(), passvisa.getPassplace(),
				str_to_date_formc(passvisa.getPassdate()), str_to_date_formc(passvisa.getPassexpdate()),
				passvisa.getPasscoun(), passvisa.getVisanum(), str_to_date_formc(passvisa.getVisadate()),
				str_to_date_formc(passvisa.getVisaexpdate()), passvisa.getVisatype(), passvisa.getVisaplace(),
				passvisa.getVisasubtype(), passvisa.getVisacoun(), passvisa.getForm_c_appl_id());

		if (status > 0) {
			return passvisa;
		} else {
			throw new Exception("Somthing went wrong");
		}

	}

	private void valdateArrivalDetails(FormC_ArrivalNextDest arrival) {
		String spclCatCode=fetchSpecialCategory(arrival.getForm_c_appl_id());
		 
		if(!spclCatCode.equals("1")) {
			if (arrival.getArriplace()==null || arrival.getArriplace().trim().length() == 0) {
				throw new ValidationException("Place of arrival must not be blank");
			} else if (arrival.getArriplace().trim().length() > 50) {
				throw new ValidationException("Place of arrival cannot be greater than 50 characters");
			}
			if (arrival.getArricit()==null || arrival.getArricit().trim().length() == 0) {
				throw new ValidationException("City of arrival must not be blank");
			} else if (arrival.getArricit().trim().length() > 50) {
				throw new ValidationException("City of arrival cannot be greater than 50 characters");
			}
			if (arrival.getArricoun()==null || arrival.getArricoun().trim().length() == 0) {
				throw new ValidationException("Country of arrival must not be blank");
			} else if (arrival.getArricoun().trim().length() > 3) {
				throw new ValidationException("Country of arrival cannot be greater than 3 characters");
			}
			if (arrival.getArridateind()==null || arrival.getArridateind().trim().length() == 0) {
				throw new ValidationException("Date of arrival in india must not be blank");
			}
			if (arrival.getArridatehotel()==null || arrival.getArridatehotel().trim().length() == 0) {
				throw new ValidationException("Date of arrival in hotel must not be blank");
			}
			if (arrival.getArritimehotel()==null || arrival.getArritimehotel().trim().length() == 0) {
				throw new ValidationException("Time of arrival in hotel must not be blank");
			}
		} else {
			if(arrival.getDurationofstay()==null) {
				arrival.setDurationofstay("0");
			}
		}
		
		
		/*
			 * else { arrival.setArriplace(null); arrival.setArricit(null);
			 * arrival.setArricoun(null); arrival.setArridateind(null);
			 * arrival.setArridatehotel(null); arrival.setArritimehotel(null); }
			 */
		
		
		
	}
	private void validatePassPortVisaDetailsBySpclCategory(FormC_PassPortVisa passvisa) throws ValidationException {

		 String spclCatCode=fetchSpecialCategory(passvisa.getForm_c_appl_id());
		 String nationality=fetchNationality(passvisa.getForm_c_appl_id());
		 
		 if(spclCatCode==null || nationality==null) {
			 throw new ValidationException("Personal details required. before submitting Passport and Visa details");
		 }
		
		
		boolean visaskipflg = false;
		boolean passpskipflg = false;
		if (spclCatCode.equals("1")) {
			visaskipflg = true;
			passpskipflg = true;
		}
		if (spclCatCode.equals("2")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (spclCatCode.equals("3")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (spclCatCode.equals("4")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (spclCatCode.equals("5")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (spclCatCode.equals("6")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (spclCatCode.equals("7")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (spclCatCode.equals("8")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (spclCatCode.equals("9")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (spclCatCode.equals("10")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (spclCatCode.equals("11")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (spclCatCode.equals("12")) // lost of passport on 01/01/2013
		{
			visaskipflg = true;
			passpskipflg = false;
		}

		if (passpskipflg == false) {
			// passport no
			if (passvisa.getPassnum()==null || passvisa.getPassnum().trim().length() == 0) {
				throw new ValidationException("Passport No must not be blank");
			} else if (passvisa.getPassnum().trim().length() > 20) {
				throw new ValidationException("Passport number cannot be greater than 20 characters");
			}
			/*
			 * else if(!(checkAlfaNumeric(formc.getPassnum()))) { throw new
			 * Exception("Passport number must contain only alphanumeric character"); }
			 */

			// passport place(City) of issue
			if (passvisa.getPassplace()==null || passvisa.getPassplace().trim().length() == 0) {
				throw new ValidationException("Passport issuing City must not be blank");
			} else if (passvisa.getPassplace().trim().length() > 50) {
				throw new ValidationException("Passport issuing City must not be greater than 50 characters");
			}
			/*
			 * else if(!(Validation.isAddress(formc.getPassplace()))) { throw new
			 * Exception("Passport issuing City must not contain special character other than .-,/:()"
			 * ); }
			 */

			// passport place(country) of issue
			
			if (passvisa.getPasscoun()==null || passvisa.getPasscoun().trim().length() == 0) {
				throw new ValidationException("Please select Passport issuing Country");
			} else if (passvisa.getPasscoun().trim().length() > 3) {
				throw new ValidationException("Please select correct Passport issuing Country");
			}
			/*
			 * else if
			 * (!(Validation.isAlphabates(applicx.getApplicant_passport_issue_country().trim
			 * ()))) { throw new
			 * Exception("Please select correct Passport issuing Country"); }
			 */

			// passport date of issue
			if (passvisa.getPassdate()==null ||  passvisa.getPassdate().trim().length() == 0) {
				throw new ValidationException("Passport Date of issue must not be blank");
			}
			/*
			 * else
			 * if(!(Validation.isValiddateofformat(applicx.getApplicant_passpdoissue().trim(
			 * )))) { throw new Exception("Please enter correct Passport Date of issue "); }
			 */

			// passport expiry date
			if (!(nationality.equals("NPL") || nationality.equals("BTN"))) {
				if (passvisa.getPassexpdate()==null || passvisa.getPassexpdate().trim().length() == 0) {
					throw new ValidationException("Passport expiry date must not be blank");
				}
				/*
				 * else
				 * if(!(Validation.isValiddateofformat(applicx.getApplicant_passpvalidtill().
				 * trim()))) { throw new
				 * Exception("Please enter correct Passport expiry date "); }
				 */
			}
		}

		if (visaskipflg == false) {
			if (!nationality.equals("NPL") && !nationality.equals("BTN")
					&& visaskipflg == false) {
				// visa no
				if (passvisa.getVisanum()==null || passvisa.getVisanum().trim().length() == 0) {
					throw new ValidationException("Visa No must not be blank");
				} else if (passvisa.getVisanum().trim().length() > 20) {
					throw new ValidationException("Visa number cannot be greater than 20 characters");
				}
				/*
				 * else if(!(Validation.isValiddAlphanumeric(applicx.getApplicant_visano()))) {
				 * throw new Exception("Visa number must contain only alphanumeric character");
				 * }
				 */

				// visa place(City) of issue
				if (passvisa.getVisaplace()==null || passvisa.getVisaplace().trim().length() == 0) {
					throw new ValidationException("Visa issuing City must not be blank");
				} else if (passvisa.getVisaplace().trim().length() > 50) {
					throw new ValidationException("Visa issuing City must not be greater than 50 characters");
				}
				/*
				 * else if(!(Validation.isAddress(applicx.getApplicant_visaplcoissue()))) {
				 * throw new
				 * Exception("Visa issuing City must not contain special character other than .-,/:()"
				 * ); }
				 */

				// visa place(country) of issue
				if (passvisa.getVisacoun()==null || passvisa.getVisacoun().trim().length() == 0) {
					throw new ValidationException("Please select Visa issuing Country");
				} else if (passvisa.getVisacoun().trim().length() > 3) {
					throw new ValidationException("Please select correct Visa issuing Country");
				}
				/*
				 * else if
				 * (!(Validation.isAlphabates(applicx.getApplicant_visa_issue_country().trim()))
				 * ) { throw new Exception("Please select correct Visa issuing Country"); }
				 */

				// visa date of issue
				if (passvisa.getVisadate()==null || passvisa.getVisadate().trim().length() == 0) {
					throw new ValidationException("Visa Date of issue must not be blank");
				}
				/*
				 * else
				 * if(!(Validation.isValiddateofformat(applicx.getApplicant_visadoissue().trim()
				 * ))) { throw new Exception("Please enter correct Visa Date of issue "); }
				 */

				// visa expiry date
				if (!(spclCatCode.equals("10"))) {
					if (passvisa.getVisaexpdate()==null || passvisa.getVisaexpdate().trim().length() == 0) {
						throw new ValidationException("Visa expiry date must not be blank");
					}
					/*
					 * else
					 * if(!(Validation.isValiddateofformat(applicx.getApplicant_visavalidtill().trim
					 * ()))) { throw new Exception("Please enter correct Visa expiry date "); }
					 */
				}

				// visa type
				if (passvisa.getVisatype()==null || passvisa.getVisatype().trim().length() == 0) {
					throw new ValidationException("Please select Type of visa ");
				} else if (passvisa.getVisatype().trim().length() > 2) {
					throw new ValidationException("Please select correct Type of visa ");
				}
				/*
				 * else
				 * if(!(Validation.isValiddAlphanumeric(applicx.getApplicant_visatype().trim()))
				 * ) { throw new Exception("Please select correct Type of visa  "); }
				 */
			}
		}

		

	
		
	}

	private String fetchNationality(String form_c_appl_id) {



		String result = "";

		try {
			String query = "select nationality from fr_formc_trans_temp where form_c_appl_id=?";
			result = jdbcFormC.queryForObject(query, new Object[] { form_c_appl_id }, String.class);
			System.out.println("-------------nationality-----------------" + result);
		} catch (Exception e) {
			throw new ValidationException("Invalid Application Id. Can't proceed.");
		}

		return result;

	
		
	
	}

	private String fetchSpecialCategory(String form_c_appl_id) {


		String result = "";

		try {
			String query = "select spl_category_code from fr_formc_trans_temp where form_c_appl_id=?";
			result = jdbcFormC.queryForObject(query, new Object[] { form_c_appl_id }, String.class);
			System.out.println("-------------spl_category_code-----------------" + result);
		} catch (Exception e) {
			throw new ValidationException("Invalid Application Id. Can't proceed.");
		}

		return result;

	
		
	}

	@Override
	public FormC_ArrivalNextDest submit_FormC_ArrivalNextDest(FormC_ArrivalNextDest arrival) throws Exception {

		
		valdateArrivalDetails(arrival);
		
		
		if(arrival.getNextdestcounflag().equalsIgnoreCase("O")){//
			arrival.setNextdestplaceinind(null);
			arrival.setNextdestdistinind(null);
			arrival.setNextdeststateinind(null);
		}
		if(arrival.getNextdestcounflag().equalsIgnoreCase("I")){
			arrival.setNextdestcounoutind(null);
			arrival.setNextdestcityoutind(null);
			arrival.setNextdestplaceoutind(null);
			
		}
		
		
		String query = "UPDATE fr_formc_trans_temp set "
				+ " arrived_from_place=?, arrived_from_city=?, arrived_from_country=?, date_of_arrival_in_india=?,"
				+ " date_of_arrival_in_hotel=?, time_of_arrival_in_hotel=?, intended_duration_of_stay_in_hotel=?,"
				+ " next_dest_country_flag=?, next_destination_place_in=?, next_destination_city_district_in=?,"

				+ " next_destination_state_in=?," + " next_destination_place_out=?, next_destination_city_out=?,"
				+ "  next_destination_country_out=? where form_c_appl_id=? ";

		int status = jdbcFormC.update(query, arrival.getArriplace(), arrival.getArricit(), arrival.getArricoun(),
				str_to_date_formc(arrival.getArridateind()), str_to_date_formc(arrival.getArridatehotel()),
				arrival.getArritimehotel(), Integer.valueOf(arrival.getDurationofstay()), arrival.getNextdestcounflag(),
				arrival.getNextdestplaceinind(), arrival.getNextdestdistinind(),

				arrival.getNextdeststateinind(), arrival.getNextdestplaceoutind(), arrival.getNextdestcityoutind(),
				arrival.getNextdestcounoutind(), arrival.getForm_c_appl_id());

		if (status > 0) {
			return arrival;
		} else {
			throw new Exception("Somthing went wrong");
		}
	}


	@Override
	public FormC_RefContact submit_FormC_RefContact(FormC_RefContact refDetails) throws Exception {

		String query = "UPDATE fr_formc_trans_temp set "
				+ " address_of_reference_in_india=?, city_district_of_reference_in_india=?, state_of_reference_in_india=?,"
				+ "  pincode_of_reference_in_india=?,"
				+ " contact_phone_no_in_india=?, contact_mobile_no_in_india=?, contact_phone_no_permanently_residing=?, contact_mobile_no_permanently_residing=?,"
				+ " employed_in_india=?, purpose_of_visit=? where form_c_appl_id=? ";
		/* ,spl_category_code=? refDetails.getSplcategorycode(), */
		int status = jdbcFormC.update(query, refDetails.getAddrofrefinind(), refDetails.getCityofrefinind(),
				refDetails.getStateofrefinind(), refDetails.getPincodeofref(), refDetails.getPhnnuminind(),
				refDetails.getMblnuminind(), refDetails.getPhnnum(), refDetails.getMblnum(),
				refDetails.getEmployedinind(),  refDetails.getPurposeofvisit(),
				refDetails.getForm_c_appl_id());

		if (status > 0) {
			return refDetails;
		} else {
			throw new Exception("Somthing went wrong");
		}
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
	public String finalSubmitFormC(String appid) throws Exception {

		String query = "INSERT INTO fr_formc_trans( "
				+ "	form_c_appl_id, acco_code, surname, given_name, date_of_birth_format, date_of_birth, gender, "
				+ "address_outside_india, city_outside_india, country_outside_india, address_of_reference_in_india, "
				+ "city_district_of_reference_in_india, state_of_reference_in_india, pincode_of_reference_in_india, "
				+ "nationality, passport_no, passport_place_of_issue, passport_date_of_issue, passport_expiry_date, "
				+ "visa_no, visa_date_of_issue, visa_expiry_date, visa_type, visa_place_of_issue, arrived_from_place, "
				+ " arrived_from_city, arrived_from_country, date_of_arrival_in_india, date_of_arrival_in_hotel, "
				+ "time_of_arrival_in_hotel, intended_duration_of_stay_in_hotel, employed_in_india, purpose_of_visit, "
				+ "next_dest_country_flag, next_destination_place_in, next_destination_city_district_in, "
				+ " next_destination_state_in, next_destination_place_out, next_destination_city_out, "
				+ "next_destination_country_out, contact_phone_no_in_india, contact_mobile_no_in_india, "
				+ "contact_phone_no_permanently_residing, contact_mobile_no_permanently_residing, frro_fro_code, "
				+ "entered_by, entered_on, passport_issue_country, visa_issue_country, "
				+ "spl_category_code, remark, visa_subtype_code," + " application_type) " + "  SELECT "
				+ "	form_c_appl_id, acco_code, surname, given_name, date_of_birth_format, date_of_birth, gender, "
				+ "address_outside_india, city_outside_india, country_outside_india, address_of_reference_in_india, "
				+ "city_district_of_reference_in_india, state_of_reference_in_india, pincode_of_reference_in_india, "
				+ "nationality, passport_no, passport_place_of_issue, passport_date_of_issue, passport_expiry_date, "
				+ "visa_no, visa_date_of_issue, visa_expiry_date, visa_type, visa_place_of_issue, arrived_from_place, "
				+ " arrived_from_city, arrived_from_country, date_of_arrival_in_india, date_of_arrival_in_hotel, "
				+ "time_of_arrival_in_hotel, intended_duration_of_stay_in_hotel, employed_in_india, purpose_of_visit, "
				+ "next_dest_country_flag, next_destination_place_in, next_destination_city_district_in, "
				+ " next_destination_state_in, next_destination_place_out, next_destination_city_out, "
				+ "next_destination_country_out, contact_phone_no_in_india, contact_mobile_no_in_india, "
				+ "contact_phone_no_permanently_residing, contact_mobile_no_permanently_residing, frro_fro_code, "
				+ "entered_by, entered_on, passport_issue_country, visa_issue_country, "
				+ "spl_category_code, remark, visa_subtype_code," + " 'M' "
				+ "	FROM public.fr_formc_trans_temp where form_c_appl_id=? ";

		int status = jdbcFormC.update(query, appid);

		// Insert Image from Temp to Main Table

		String moveImg = "INSERT into fr_formc_photo(applicant_id,photo,entered_by,entered_on) "
				+ " select applicant_id,photo,entered_by,entered_on from fr_formc_photo_temp where applicant_id=?";

		int status1 = jdbcFormC.update(moveImg, appid);
		if (status1 > 0) {

			jdbcFormC.update("delete from fr_formc_photo_temp where applicant_id=?", appid);

		}
		if (status > 0) {
			jdbcFormC.update("delete from fr_formc_trans_temp where form_c_appl_id=?", appid);
			return "Application Id-" + appid + " Submitted successfully.";
		} else {
			throw new Exception("Application not Submitted.");
		}

	}


	private void validateAccoCodeAndFrroCode(String accoCode, String frroCode,String userid) throws Exception {
		
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
	
	private void dateCompare(String date1,String date2,String msg) throws Exception{
		 SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		 Date d1 = sdformat.parse(date1);
	     Date d2 = sdformat.parse(date2);
	      
	      if(d1.compareTo(d2) <0) {
	    	 // System.out.println("Date 1 occurs before Date 2");
	    	 throw new ValidationException(msg) ;
	      }
	}

	@Override
	public String logoutUser(String token, String user,Date expTime) throws Exception {
		
		System.out.println("Exp time--"+ expTime);
		
		String query = "INSERT INTO invalid_token_list( "
				+ " entred_on, jwt, userid, expired_after) "
				+ "	VALUES (now(), ?, ?, ?) ";

		int status = jdbcFormC.update(query,token, user,expTime);
		
		if(status==1) {
			String updateQuery = "update fr_login_history set logout_date=clock_timestamp() " + " where login_by=? and "
					+ " login_date =(select login_date from fr_login_history where login_by=? and logout_date is null  order by login_date desc limit 1 )";
			jdbcFormC.update(updateQuery, user, user);
			return "User logout Successfully.";	
		}else {
			throw new Exception("Somthing went wrong.");
		}
		
	}

	@Override
	public String checkForValidToken(String token, String userid) throws Exception {
		
		String q2 = "select count(*) from invalid_token_list where userid=? and jwt=?";
		int res = jdbcFormC.queryForObject(q2, new Object[] { userid,token }, Integer.class);
		if(res>0) {
		   return "INVALID";
		}
		
		return "VALID";
	}
}
