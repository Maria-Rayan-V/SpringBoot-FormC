package ivfrt.frt.frro.web.formc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.validation.ValidationException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ivfrt.frt.frro.web.service.imagesave.ImageSave;
import ivfrt.frt.frro.web.service.imagesave.ImageSaveRepository;

@Repository
public class FormCServices implements FormCRepository {

	@Autowired
	@Qualifier("jdbcFormC")
	private JdbcTemplate jdbcFormC;

	@Autowired
	ImageSaveRepository imagerepo;

	@Override
	@Transactional
	public FormC insertFormCdata(FormC formc) throws Exception {
		FormC formcret = null;
		DateFormat df = new SimpleDateFormat("ddMMyy");
		Date dateobj = new Date();
		String form_c_appl_id = genFormCAppId();
		Date entered_on = dateobj;
		if (formc.getEntered_on() == null) {
			entered_on = dateobj;
		} else {
			entered_on = formc.getEntered_on();
		}
		validateFormCData(formc);

		if (formc.getImg() == null) {
			throw new Exception("Image Required!");
		}
		if (formc.getDurationofstay().trim().equals("")) {
			formc.setDurationofstay("0");
		} else {
			try {
				Integer.parseInt(formc.getDurationofstay());
			} catch (ValidationException e) {
				throw new Exception("Duration of Stay : Invalid Parameter.");
			}
		}
		// new Born
		if (formc.getSplcategorycode().equals("1")) {
			if (formc.getPassnum() != null) {
				if (formc.getPassnum().equals("")) {
					setPassposrtDetailNULL(formc);

				}
				if (formc.getPassnum() != null) {
					if (formc.getVisanum().equals("")) {
						setVisaDetailNULL(formc);

					}
				}
			}
		}

		if (formc.getSplcategorycode().equals("2") || formc.getSplcategorycode().equals("4")
				|| formc.getSplcategorycode().equals("6") || formc.getSplcategorycode().equals("7")
				|| formc.getSplcategorycode().equals("8") || formc.getSplcategorycode().equals("12")) {
			if (formc.getPassnum() != null) {
				if (formc.getVisanum().equals("")) {
					setVisaDetailNULL(formc);
				}
			}
		}
		//System.out.println("-----------------------------------" + form_c_appl_id);
		insertData_fr_formc_trans(formc, form_c_appl_id, formc.getAcco_code(), formc.getFrro_fro_code(),
				formc.getEntered_by(), entered_on);
		imagerepo.saveImage(form_c_appl_id, Base64.decodeBase64(formc.getImg().getBytes()), formc.getEntered_by());

		formcret = fetchFormCData_fr_formc_trans(form_c_appl_id);
		return formcret;

	}

	private void validateFormCData(FormC formc) throws Exception {
		boolean visaskipflg = false;
		boolean passpskipflg = false;
		if (formc.getSplcategorycode().equals("1")) {
			visaskipflg = true;
			passpskipflg = true;
		}
		if (formc.getSplcategorycode().equals("2")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("3")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("4")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("5")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("6")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("7")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("8")) {
			visaskipflg = true;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("9")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("10")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("11")) {
			visaskipflg = false;
			passpskipflg = false;
		}
		if (formc.getSplcategorycode().equals("12")) // lost of passport on 01/01/2013
		{
			visaskipflg = true;
			passpskipflg = false;
		}

		if (passpskipflg == false) {
			// passport no
			if (formc.getPassnum().trim().length() == 0) {
				throw new Exception("Passport No must not be blank");
			} else if (formc.getPassnum().trim().length() > 20) {
				throw new Exception("Passport number cannot be greater than 20 characters");
			}
			/*
			 * else if(!(checkAlfaNumeric(formc.getPassnum()))) { throw new
			 * Exception("Passport number must contain only alphanumeric character"); }
			 */

			// passport place(City) of issue
			if (formc.getPassplace().trim().length() == 0) {
				throw new Exception("Passport issuing City must not be blank");
			} else if (formc.getPassplace().trim().length() > 50) {
				throw new Exception("Passport issuing City must not be greater than 50 characters");
			}
			/*
			 * else if(!(Validation.isAddress(formc.getPassplace()))) { throw new
			 * Exception("Passport issuing City must not contain special character other than .-,/:()"
			 * ); }
			 */

			// passport place(country) of issue
			if (formc.getPasscoun().trim().length() == 0) {
				throw new Exception("Please select Passport issuing Country");
			} else if (formc.getPasscoun().trim().length() > 3) {
				throw new Exception("Please select correct Passport issuing Country");
			}
			/*
			 * else if
			 * (!(Validation.isAlphabates(applicx.getApplicant_passport_issue_country().trim
			 * ()))) { throw new
			 * Exception("Please select correct Passport issuing Country"); }
			 */

			// passport date of issue
			if (formc.getPassdate().trim().length() == 0) {
				throw new Exception("Passport Date of issue must not be blank");
			}
			/*
			 * else
			 * if(!(Validation.isValiddateofformat(applicx.getApplicant_passpdoissue().trim(
			 * )))) { throw new Exception("Please enter correct Passport Date of issue "); }
			 */

			// passport expiry date
			if (!(formc.getNationality().equals("NPL") || formc.getNationality().equals("BTN"))) {
				if (formc.getPassexpdate().trim().length() == 0) {
					throw new Exception("Passport expiry date must not be blank");
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
			if (!formc.getNationality().equals("NPL") && !formc.getNationality().equals("BTN")
					&& visaskipflg == false) {
				// visa no
				if (formc.getVisanum().trim().length() == 0) {
					throw new Exception("Visa No must not be blank");
				} else if (formc.getVisanum().trim().length() > 20) {
					throw new Exception("Visa number cannot be greater than 20 characters");
				}
				/*
				 * else if(!(Validation.isValiddAlphanumeric(applicx.getApplicant_visano()))) {
				 * throw new Exception("Visa number must contain only alphanumeric character");
				 * }
				 */

				// visa place(City) of issue
				if (formc.getVisaplace().trim().length() == 0) {
					throw new Exception("Visa issuing City must not be blank");
				} else if (formc.getVisaplace().trim().length() > 50) {
					throw new Exception("Visa issuing City must not be greater than 50 characters");
				}
				/*
				 * else if(!(Validation.isAddress(applicx.getApplicant_visaplcoissue()))) {
				 * throw new
				 * Exception("Visa issuing City must not contain special character other than .-,/:()"
				 * ); }
				 */

				// visa place(country) of issue
				if (formc.getVisacoun().trim().length() == 0) {
					throw new Exception("Please select Visa issuing Country");
				} else if (formc.getVisacoun().trim().length() > 3) {
					throw new Exception("Please select correct Visa issuing Country");
				}
				/*
				 * else if
				 * (!(Validation.isAlphabates(applicx.getApplicant_visa_issue_country().trim()))
				 * ) { throw new Exception("Please select correct Visa issuing Country"); }
				 */

				// visa date of issue
				if (formc.getVisadate().trim().length() == 0) {
					throw new Exception("Visa Date of issue must not be blank");
				}
				/*
				 * else
				 * if(!(Validation.isValiddateofformat(applicx.getApplicant_visadoissue().trim()
				 * ))) { throw new Exception("Please enter correct Visa Date of issue "); }
				 */

				// visa expiry date
				if (!(formc.getSplcategorycode().equals("10"))) {
					if (formc.getVisaexpdate().trim().length() == 0) {
						throw new Exception("Visa expiry date must not be blank");
					}
					/*
					 * else
					 * if(!(Validation.isValiddateofformat(applicx.getApplicant_visavalidtill().trim
					 * ()))) { throw new Exception("Please enter correct Visa expiry date "); }
					 */
				}

				// visa type
				if (formc.getVisatype().trim().length() == 0) {
					throw new Exception("Please select Type of visa ");
				} else if (formc.getVisatype().trim().length() > 2) {
					throw new Exception("Please select correct Type of visa ");
				}
				/*
				 * else
				 * if(!(Validation.isValiddAlphanumeric(applicx.getApplicant_visatype().trim()))
				 * ) { throw new Exception("Please select correct Type of visa  "); }
				 */
			}
		}

		if (formc.getImg().contains("<") || formc.getImg().contains(">") || formc.getImg().contains(".exe")
				|| formc.getImg().contains(".bat") || formc.getImg().contains(".com") || formc.getImg().contains(".cmd")
				|| formc.getImg().contains(".run")) {
			throw new Exception("Invalid Image.");
		}

	}

	private boolean checkAlfaNumeric(String str) {
		// "^[a-zA-Z0-9]*$"
		return true;
	}

	private void setVisaDetailNULL(FormC formc) {
		formc.setVisanum(null);
		formc.setVisadate(null);
		formc.setVisacoun(null);
		formc.setVisatype(null);
		formc.setVisaplace(null);
		formc.setVisasubtype(null);

	}

	private void setPassposrtDetailNULL(FormC formc) {
		formc.setPassnum(null);
		formc.setPasscoun(null);
		formc.setPassexpdate(null);
		formc.setPassplace(null);
		formc.setPassplace(null);

	}

	private FormC fetchFormCData_fr_formc_trans(String form_c_appl_id) {
		String qry = "SELECT form_c_appl_id, acco_code, surname, given_name, date_of_birth_format, "
				+ " to_char(date_of_birth,'DD/MM/YYYY') as date_of_birth , "
				+ " (select gender_desc from fr_gender where gender_code=gender) as gender  , "
				+ " address_outside_india, city_outside_india, "
				+ " country_outside_india, address_of_reference_in_india, "
				+ " (select city_district_name from city_district where state_code=state_of_reference_in_india and city_district_code=city_district_of_reference_in_india ) as  city_district_of_reference_in_india,"
				+ " (select state_name from fr_states where state_code=state_of_reference_in_india) as state_of_reference_in_india, "
				+ " pincode_of_reference_in_india,(select country_name from fr_country where country_code= nationality) as nationality, passport_no, passport_place_of_issue, "
				+ " to_char(passport_date_of_issue,'DD/MM/YYYY') as passport_date_of_issue ,to_char(passport_expiry_date, 'DD/MM/YYYY') as passport_expiry_date,"
				+ " visa_no, to_char(visa_date_of_issue,'DD/MM/YYYY') as visa_date_of_issue , "
				+ " to_char(visa_expiry_date,'DD/MM/YYYY') as visa_expiry_date , visa_type, visa_place_of_issue, arrived_from_place, arrived_from_city, "
				+ " (select country_name from fr_country where country_code=arrived_from_country) as arrived_from_country, "
				+ " to_char(date_of_arrival_in_india,'DD/MM/YYYY') as date_of_arrival_in_india, to_char(date_of_arrival_in_hotel,'DD/MM/YYYY') as date_of_arrival_in_hotel, "
				+ " time_of_arrival_in_hotel, intended_duration_of_stay_in_hotel, case when employed_in_india='Y' then 'Yes' when employed_in_india='N' then 'No' else employed_in_india end as employed_in_india  , "
				+ " purpose_of_visit, next_dest_country_flag, next_destination_place_in, "
				+ " (select city_district_name from city_district where state_code=next_destination_state_in and city_district_code=next_destination_city_district_in ) as next_destination_city_district_in,"
				+ " (select state_name from fr_states where state_code=next_destination_state_in) as next_destination_state_in, "
				+ " next_destination_place_out, next_destination_city_out, next_destination_country_out, "
				+ " contact_phone_no_in_india, contact_mobile_no_in_india, "
				+ " contact_phone_no_permanently_residing, contact_mobile_no_permanently_residing, "
				+ " frro_fro_code, entered_by, entered_on, passport_issue_country, "
				+ " visa_issue_country, spl_category_code, remark, visa_subtype_code "
				+ "	FROM fr_formc_trans where form_c_appl_id=? ";

		return jdbcFormC.queryForObject(qry, new Object[] { form_c_appl_id }, (rs, rowNum) -> new FormC(
				rs.getString("form_c_appl_id"), rs.getString("acco_code"), rs.getString("remark"),
				rs.getString("entered_by"), rs.getDate("entered_on"), rs.getString("given_name"),
				rs.getString("surname"), rs.getString("gender"), rs.getString("date_of_birth_format"),
				rs.getString("date_of_birth"), rs.getString("nationality"), rs.getString("address_outside_india"),
				rs.getString("city_outside_india"), rs.getString("country_outside_india"), rs.getString("passport_no"),
				rs.getString("passport_place_of_issue"), rs.getString("passport_issue_country"),
				rs.getString("passport_date_of_issue"), rs.getString("passport_expiry_date"), rs.getString("visa_no"),
				rs.getString("visa_place_of_issue"), rs.getString("visa_issue_country"),
				rs.getString("visa_date_of_issue"), rs.getString("visa_expiry_date"), rs.getString("visa_type"),
				rs.getString("visa_subtype_code"), rs.getString("arrived_from_place"),
				rs.getString("arrived_from_city"), rs.getString("arrived_from_country"),
				rs.getString("date_of_arrival_in_india"), rs.getString("date_of_arrival_in_hotel"),
				rs.getString("time_of_arrival_in_hotel"), rs.getString("intended_duration_of_stay_in_hotel"),
				rs.getString("next_destination_place_in"), rs.getString("next_destination_city_district_in"),
				rs.getString("next_destination_state_in"), rs.getString("next_dest_country_flag"),
				rs.getString("next_destination_place_out"), rs.getString("next_destination_city_out"),
				rs.getString("next_destination_country_out"), rs.getString("address_of_reference_in_india"),
				rs.getString("state_of_reference_in_india"), rs.getString("city_district_of_reference_in_india"),
				rs.getString("pincode_of_reference_in_india"), rs.getString("contact_mobile_no_in_india"),
				rs.getString("contact_phone_no_in_india"), rs.getString("contact_mobile_no_permanently_residing"),
				rs.getString("contact_phone_no_permanently_residing"), rs.getString("employed_in_india"),
				rs.getString("spl_category_code"), rs.getString("purpose_of_visit")));
	}

	private int insertData_fr_formc_trans(FormC formc, String form_c_appl_id, String acco_code, String frro_fro_code,
			String entered_by, Date entered_on) {
		int imgInsert = 0;
		String query = "";

		query = "INSERT INTO fr_formc_trans "
				+ " ( form_c_appl_id, acco_code, surname, given_name, date_of_birth_format, date_of_birth, gender, "
				+ "		address_outside_india, city_outside_india, country_outside_india,"
				+ "		 address_of_reference_in_india, city_district_of_reference_in_india, state_of_reference_in_india, pincode_of_reference_in_india,"
				+ " nationality, passport_no, passport_place_of_issue, passport_date_of_issue, passport_expiry_date,"
				+ " visa_no, visa_date_of_issue, visa_expiry_date, visa_type, visa_place_of_issue, "
				+ " arrived_from_place, arrived_from_city, arrived_from_country, date_of_arrival_in_india, date_of_arrival_in_hotel, time_of_arrival_in_hotel, intended_duration_of_stay_in_hotel,"
				+ " employed_in_india, purpose_of_visit, next_dest_country_flag, next_destination_place_in, next_destination_city_district_in, next_destination_state_in, "
				+ " next_destination_place_out, next_destination_city_out, next_destination_country_out,"
				+ " contact_phone_no_in_india, contact_mobile_no_in_india, contact_phone_no_permanently_residing, contact_mobile_no_permanently_residing,   passport_issue_country, visa_issue_country, spl_category_code, visa_subtype_code,"
				+ " frro_fro_code,entered_by,entered_on,remark,application_type) "
				+ "	VALUES ( ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int updatekey = jdbcFormC.update(query, form_c_appl_id, acco_code, formc.getSurname(), formc.getName(),
				formc.getDobformat(), str_to_date_formc(formc.getDob()), formc.getGender(), formc.getAddroutind(),
				formc.getCityoutind(), formc.getCounoutind(), formc.getAddrofrefinind(), formc.getCityofrefinind(),
				formc.getStateofrefinind(), formc.getPincodeofref(), formc.getNationality(), formc.getPassnum(),
				formc.getPassplace(), str_to_date_formc(formc.getPassdate()), str_to_date_formc(formc.getPassexpdate()),
				formc.getVisanum(), str_to_date_formc(formc.getVisadate()), str_to_date_formc(formc.getVisaexpdate()),
				formc.getVisatype(), formc.getVisaplace(), formc.getArriplace(), formc.getArricit(),
				formc.getArricoun(), str_to_date_formc(formc.getArridateind()),
				str_to_date_formc(formc.getArridatehotel()), formc.getArritimehotel(),
				Integer.valueOf(formc.getDurationofstay()), formc.getEmployedinind(), formc.getPurposeofvisit(),
				formc.getNextdestcounflag(), formc.getNextdestplaceinind(), formc.getNextdestdistinind(),
				formc.getNextdeststateinind(), formc.getNextdestplaceoutind(), formc.getNextdestcityoutind(),
				formc.getNextdestcounoutind(), formc.getPhnnuminind(), formc.getMblnuminind(), formc.getPhnnum(),
				formc.getMblnum(), formc.getPasscoun(), formc.getVisacoun(), formc.getSplcategorycode(),
				formc.getVisasubtype(), frro_fro_code, entered_by, entered_on, formc.getRemark(), "M");
		return updatekey;
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
	/*
	 * protected String getSaltString() { String SALTCHARS =
	 * "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; StringBuilder salt = new
	 * StringBuilder(); Random rnd = new Random(); while (salt.length() < 7) { //
	 * length of the random string. int index = (int) (rnd.nextFloat() *
	 * SALTCHARS.length()); salt.append(SALTCHARS.charAt(index)); } String saltStr =
	 * salt.toString(); return saltStr;
	 * 
	 * }
	 */

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
			// formatter = new java.text.SimpleDateFormat("MM/dd/yyyy");
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
//(select city_district_name from city_district where state_code=next_destination_state_in and city_district_code=next_destination_city_district_in ) as
//(select state_name from fr_states where state_code=next_destination_state_in) as
	//(select country_name from fr_country where country_code=arrived_from_country) as
	//(select city_district_name from city_district where state_code=state_of_reference_in_india and city_district_code=city_district_of_reference_in_india ) as 
	//(select state_name from fr_states where state_code=state_of_reference_in_india) as 
	@Override
	public List<FormC> findByAppId(String appid) {

		String qry = "SELECT form_c_appl_id, acco_code, surname, given_name, date_of_birth_format, "
				+ " to_char(date_of_birth,'DD/MM/YYYY') as date_of_birth ,gender  , "
				+ " address_outside_india, city_outside_india, "
				+ " country_outside_india, address_of_reference_in_india, "
				+ "  city_district_of_reference_in_india,"
				+ " state_of_reference_in_india, "
				+ " pincode_of_reference_in_india, nationality, passport_no, passport_place_of_issue, "
				+ " to_char(passport_date_of_issue,'DD/MM/YYYY') as passport_date_of_issue ,to_char(passport_expiry_date, 'DD/MM/YYYY') as passport_expiry_date,"
				+ " visa_no, to_char(visa_date_of_issue,'DD/MM/YYYY') as visa_date_of_issue , "
				+ " to_char(visa_expiry_date,'DD/MM/YYYY') as visa_expiry_date , visa_type, visa_place_of_issue, arrived_from_place, arrived_from_city, "
				+ "  arrived_from_country, "
				+ " to_char(date_of_arrival_in_india,'DD/MM/YYYY') as date_of_arrival_in_india , to_char(date_of_arrival_in_hotel,'DD/MM/YYYY') as date_of_arrival_in_hotel, "
				+ " time_of_arrival_in_hotel, intended_duration_of_stay_in_hotel, case when employed_in_india='Y' then 'Yes' when employed_in_india='N' then 'No' else employed_in_india end as employed_in_india  , "
				+ " purpose_of_visit,(select purpose_desc from fr_purpose where purpose_code=purpose_of_visit) as purpose_of_visitDesc, next_dest_country_flag, next_destination_place_in, "
				+ "  next_destination_city_district_in,"
				+ "  next_destination_state_in, "
				+ " next_destination_place_out, next_destination_city_out, next_destination_country_out, "
				+ " contact_phone_no_in_india, contact_mobile_no_in_india, "
				+ " contact_phone_no_permanently_residing, contact_mobile_no_permanently_residing, "
				+ " frro_fro_code, entered_by, entered_on, passport_issue_country, "
				+ " visa_issue_country, spl_category_code, remark, visa_subtype_code "
				+ "	FROM fr_formc_trans where form_c_appl_id=? ";

		return jdbcFormC.query(qry, new Object[] { appid }, (rs, rowNum) -> new FormC(rs.getString("form_c_appl_id"),
				rs.getString("acco_code"), rs.getString("remark"), rs.getString("entered_by"), rs.getDate("entered_on"),
				rs.getString("given_name"), rs.getString("surname"), rs.getString("gender"),
				fetchGenderDesc(rs.getString("gender")), rs.getString("date_of_birth_format"),
				rs.getString("date_of_birth"), rs.getString("nationality"),
				fetchCountryDesc(rs.getString("nationality")), rs.getString("address_outside_india"),
				rs.getString("city_outside_india"), rs.getString("country_outside_india"),
				fetchCountryDesc(rs.getString("country_outside_india")), rs.getString("passport_no"),
				rs.getString("passport_place_of_issue"), rs.getString("passport_issue_country"),fetchCountryDesc(rs.getString("passport_issue_country")),
				rs.getString("passport_date_of_issue"), rs.getString("passport_expiry_date"), rs.getString("visa_no"),
				rs.getString("visa_place_of_issue"), rs.getString("visa_issue_country"),fetchCountryDesc(rs.getString("visa_issue_country")),
				rs.getString("visa_date_of_issue"), rs.getString("visa_expiry_date"), rs.getString("visa_type"),fetchVisaTypeDesc(rs.getString("visa_type")),
				rs.getString("visa_subtype_code"),fetchVisaSubtypeTypeDesc(rs.getString("visa_subtype_code")), rs.getString("arrived_from_place"),
				rs.getString("arrived_from_city"), rs.getString("arrived_from_country"),fetchCountryDesc(rs.getString("arrived_from_country")),
				rs.getString("date_of_arrival_in_india"), rs.getString("date_of_arrival_in_hotel"),
				rs.getString("time_of_arrival_in_hotel"), rs.getString("intended_duration_of_stay_in_hotel"),
				rs.getString("next_destination_place_in"), rs.getString("next_destination_city_district_in"),fetchCityDistDesc(rs.getString("next_destination_city_district_in"),rs.getString("next_destination_state_in")),
				rs.getString("next_destination_state_in"),fetchStateDesc(rs.getString("next_destination_state_in")), rs.getString("next_dest_country_flag"),
				rs.getString("next_destination_place_out"), rs.getString("next_destination_city_out"),
				rs.getString("next_destination_country_out"),fetchCountryDesc(rs.getString("next_destination_country_out")), rs.getString("address_of_reference_in_india"),
				rs.getString("state_of_reference_in_india"),fetchStateDesc(rs.getString("state_of_reference_in_india")), rs.getString("city_district_of_reference_in_india"),fetchCityDistDesc(rs.getString("city_district_of_reference_in_india"),rs.getString("state_of_reference_in_india")),
				rs.getString("pincode_of_reference_in_india"), rs.getString("contact_mobile_no_in_india"),
				rs.getString("contact_phone_no_in_india"), rs.getString("contact_mobile_no_permanently_residing"),
				rs.getString("contact_phone_no_permanently_residing"), rs.getString("employed_in_india"),
				rs.getString("spl_category_code"),fetchSplCatDesc(rs.getString("spl_category_code")), rs.getString("purpose_of_visit"),rs.getString("purpose_of_visitDesc"), rs.getString("frro_fro_code"),
				fetchImg(appid)));

	}

	private String fetchImg(String appid) {
		String response = "";
		ImageSave img = imagerepo.downLoadImage(appid);
		if (img != null) {
			response = Base64.encodeBase64String(img.getImgData());
		}
		return response;
	}

	private String fetchImgForEdit(String appid) {
		String response = "";
		ImageSave img = imagerepo.downLoadImageEDIT(appid);
		if (img != null) {
			response = Base64.encodeBase64String(img.getImgData());
		}
		return response;
	}

	@Override
	public List<FormC> findByUserId(String userid, String acco_code) {
		return jdbcFormC.query("SELECT form_c_appl_id, acco_code, surname, given_name,  "
				+ "(select country_name from fr_country where country_code= nationality) as nationality, passport_no "
				+ " FROM fr_formc_trans where acco_code=? and entered_by=? ", new Object[] { acco_code, userid },
				(rs, rowNum) -> new FormC(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("passport_no")));
	}

	@Override
	public List<FormC> findByPassnumAndNat(String passportNo, String nationality) {

		String query = "select form_c_appl_id,acco_code,remark,entered_by, entered_on ,given_name,surname,gender,"
				+ " date_of_birth_format,to_char(date_of_birth,'DD/MM/YYYY') as date_of_birth,nationality,"
				+ "address_outside_india,city_outside_india,country_outside_india,"
				+ "passport_no,passport_place_of_issue,passport_issue_country,to_char(passport_date_of_issue,'DD/MM/YYYY') as passport_date_of_issue,"
				+ " to_char(passport_expiry_date,'DD/MM/YYYY') as passport_expiry_date,visa_no,"
				+ " visa_place_of_issue,visa_issue_country,to_char(visa_date_of_issue,'DD/MM/YYYY') as visa_date_of_issue,"
				+ " to_char(visa_expiry_date,'DD/MM/YYYY') as visa_expiry_date,visa_type,visa_subtype_code,"
				+ " arrived_from_place,arrived_from_city,arrived_from_country,to_char(date_of_arrival_in_india,'DD/MM/YYYY') as date_of_arrival_in_india,"
				+ " to_char(date_of_arrival_in_hotel,'DD/MM/YYYY') as date_of_arrival_in_hotel ,"
				+ "time_of_arrival_in_hotel,intended_duration_of_stay_in_hotel,next_destination_place_in,next_destination_city_district_in,"
				+ "next_destination_state_in,next_dest_country_flag,next_destination_place_out,next_destination_city_out,next_destination_country_out,"
				+ "address_of_reference_in_india,state_of_reference_in_india,city_district_of_reference_in_india,pincode_of_reference_in_india,"
				+ "contact_mobile_no_in_india,contact_phone_no_in_india,contact_mobile_no_permanently_residing,contact_phone_no_permanently_residing, "
				+ " employed_in_india,spl_category_code,purpose_of_visit,frro_fro_code from fr_formc_trans "
				+ " where country_outside_india=? and passport_no=? order by entered_on desc  limit 1";
		return jdbcFormC.query(query, new Object[] { nationality, passportNo }, (rs, rowNum) -> new FormC(
				rs.getString("form_c_appl_id"), rs.getString("acco_code"), rs.getString("remark"),
				rs.getString("entered_by"), rs.getDate("entered_on"), rs.getString("given_name"),
				rs.getString("surname"), rs.getString("gender"), fetchGenderDesc(rs.getString("gender")),
				rs.getString("date_of_birth_format"), rs.getString("date_of_birth"), rs.getString("nationality"),
				fetchCountryDesc(rs.getString("nationality")), rs.getString("address_outside_india"),
				rs.getString("city_outside_india"), rs.getString("country_outside_india"),
				fetchCountryDesc(rs.getString("country_outside_india")), rs.getString("passport_no"),
				rs.getString("passport_place_of_issue"), rs.getString("passport_issue_country"),
				rs.getString("passport_date_of_issue"), rs.getString("passport_expiry_date"), rs.getString("visa_no"),
				rs.getString("visa_place_of_issue"), rs.getString("visa_issue_country"),
				rs.getString("visa_date_of_issue"), rs.getString("visa_expiry_date"), rs.getString("visa_type"),
				rs.getString("visa_subtype_code"), rs.getString("arrived_from_place"),
				rs.getString("arrived_from_city"), rs.getString("arrived_from_country"),
				rs.getString("date_of_arrival_in_india"), rs.getString("date_of_arrival_in_hotel"),
				rs.getString("time_of_arrival_in_hotel"), rs.getString("intended_duration_of_stay_in_hotel"),
				rs.getString("next_destination_place_in"), rs.getString("next_destination_city_district_in"),
				rs.getString("next_destination_state_in"), rs.getString("next_dest_country_flag"),
				rs.getString("next_destination_place_out"), rs.getString("next_destination_city_out"),
				rs.getString("next_destination_country_out"), rs.getString("address_of_reference_in_india"),
				rs.getString("state_of_reference_in_india"), rs.getString("city_district_of_reference_in_india"),
				rs.getString("pincode_of_reference_in_india"), rs.getString("contact_mobile_no_in_india"),
				rs.getString("contact_phone_no_in_india"), rs.getString("contact_mobile_no_permanently_residing"),
				rs.getString("contact_phone_no_permanently_residing"), rs.getString("employed_in_india"),
				rs.getString("spl_category_code"), rs.getString("purpose_of_visit"), rs.getString("frro_fro_code"),
				fetchImg(rs.getString("form_c_appl_id"))));

	}

	@Override
	public List<ApplicationList> pendingApplicationList(String userid, String frroCode) {

		return jdbcFormC.query("SELECT form_c_appl_id, acco_code, surname, given_name,  "
				+ "(select country_name from fr_country where country_code= nationality) as nationalityDesc,nationality,country_outside_india,(select country_name from fr_country where country_code= country_outside_india) as country_outside_indiaDesc, passport_no "
				+ " FROM fr_formc_trans_temp where frro_fro_code=? and entered_by=? ",
				new Object[] { frroCode, userid },
				(rs, rowNum) -> new ApplicationList(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("nationalityDesc"),
						rs.getString("country_outside_india"), rs.getString("country_outside_indiaDesc"),
						rs.getString("passport_no")));

	}

	@Override
	public List<ApplicationList> applicationList(String userid, String frroCode) {

		return jdbcFormC.query("SELECT form_c_appl_id, acco_code, surname, given_name,  "
				+ "(select country_name from fr_country where country_code= nationality) as nationalityDesc,nationality,country_outside_india,(select country_name from fr_country where country_code= country_outside_india) as country_outside_indiaDesc, passport_no "
				+ " FROM fr_formc_trans where frro_fro_code=? and entered_by=? order by entered_on desc ",
				new Object[] { frroCode, userid },
				(rs, rowNum) -> new ApplicationList(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("nationalityDesc"),
						rs.getString("country_outside_india"), rs.getString("country_outside_indiaDesc"),
						rs.getString("passport_no")));

	}

	@Override
	public List<FormC> findByAppIdForEdit(String appid) {

		String qry = "SELECT form_c_appl_id, acco_code, surname, given_name, date_of_birth_format, "
				+ " to_char(date_of_birth,'DD/MM/YYYY') as date_of_birth , " + " gender  , "
				+ " address_outside_india, city_outside_india, "
				+ " country_outside_india, address_of_reference_in_india,city_district_of_reference_in_india, "
				+ " (select city_district_name from city_district where state_code=state_of_reference_in_india and city_district_code=city_district_of_reference_in_india ) as  city_district_of_reference_in_indiaDesc,"
				+ " state_of_reference_in_india,(select state_name from fr_states where state_code=state_of_reference_in_india) as state_of_reference_in_indiaDesc, "
				+ " pincode_of_reference_in_india,nationality, passport_no, passport_place_of_issue, "
				+ " to_char(passport_date_of_issue,'DD/MM/YYYY') as passport_date_of_issue ,to_char(passport_expiry_date, 'DD/MM/YYYY') as passport_expiry_date,"
				+ " visa_no, to_char(visa_date_of_issue,'DD/MM/YYYY') as visa_date_of_issue , "
				+ " to_char(visa_expiry_date,'DD/MM/YYYY') as visa_expiry_date , visa_type, visa_place_of_issue, arrived_from_place, arrived_from_city, "
				+ " arrived_from_country, "
				+ " to_char(date_of_arrival_in_india,'DD/MM/YYYY') as date_of_arrival_in_india , to_char(date_of_arrival_in_hotel,'DD/MM/YYYY') as date_of_arrival_in_hotel, "
				+ " time_of_arrival_in_hotel, intended_duration_of_stay_in_hotel,employed_in_india , "
				+ " purpose_of_visit,(select purpose_desc from fr_purpose where purpose_code=purpose_of_visit) as purpose_of_visitDesc,"
				+ " next_dest_country_flag, next_destination_place_in, "
				+ " next_destination_city_district_in,(select city_district_name from city_district where state_code=next_destination_state_in and city_district_code=next_destination_city_district_in ) as next_destination_city_district_inDesc,"
				+ " next_destination_state_in,(select state_name from fr_states where state_code=next_destination_state_in) as next_destination_state_inDesc, "
				+ " next_destination_place_out, next_destination_city_out, next_destination_country_out, "
				+ " contact_phone_no_in_india, contact_mobile_no_in_india, "
				+ " contact_phone_no_permanently_residing, contact_mobile_no_permanently_residing, "
				+ " frro_fro_code, entered_by, entered_on, passport_issue_country, " + " visa_issue_country,"
				+ " spl_category_code," + " remark, visa_subtype_code "
				+ "	FROM fr_formc_trans_temp where form_c_appl_id=? ";

		return jdbcFormC.query(qry, new Object[] { appid }, (rs, rowNum) -> new FormC(rs.getString("form_c_appl_id"),
				rs.getString("acco_code"), rs.getString("remark"), rs.getString("entered_by"), rs.getDate("entered_on"),
				rs.getString("given_name"), rs.getString("surname"), rs.getString("gender"),
				fetchGenderDesc(rs.getString("gender")), rs.getString("date_of_birth_format"),
				rs.getString("date_of_birth"), rs.getString("nationality"),
				fetchCountryDesc(rs.getString("nationality")), rs.getString("address_outside_india"),
				rs.getString("city_outside_india"), rs.getString("country_outside_india"),
				fetchCountryDesc(rs.getString("country_outside_india")), rs.getString("passport_no"),
				rs.getString("passport_place_of_issue"), rs.getString("passport_issue_country"),
				fetchCountryDesc(rs.getString("passport_issue_country")), rs.getString("passport_date_of_issue"),
				rs.getString("passport_expiry_date"), rs.getString("visa_no"), rs.getString("visa_place_of_issue"),
				rs.getString("visa_issue_country"), fetchCountryDesc(rs.getString("visa_issue_country")),
				rs.getString("visa_date_of_issue"), rs.getString("visa_expiry_date"), rs.getString("visa_type"), "",
				rs.getString("visa_subtype_code"), "", rs.getString("arrived_from_place"),
				rs.getString("arrived_from_city"), rs.getString("arrived_from_country"),
				fetchCountryDesc(rs.getString("arrived_from_country")), rs.getString("date_of_arrival_in_india"),
				rs.getString("date_of_arrival_in_hotel"), rs.getString("time_of_arrival_in_hotel"),
				rs.getString("intended_duration_of_stay_in_hotel"), rs.getString("next_destination_place_in"),
				rs.getString("next_destination_city_district_in"),
				rs.getString("next_destination_city_district_inDesc"), rs.getString("next_destination_state_in"),
				rs.getString("next_destination_state_inDesc"), rs.getString("next_dest_country_flag"),
				rs.getString("next_destination_place_out"), rs.getString("next_destination_city_out"),
				rs.getString("next_destination_country_out"),fetchCountryDesc(rs.getString("next_destination_country_out")), rs.getString("address_of_reference_in_india"),
				rs.getString("state_of_reference_in_india"), rs.getString("state_of_reference_in_indiaDesc"),
				rs.getString("city_district_of_reference_in_india"),
				rs.getString("city_district_of_reference_in_indiaDesc"), rs.getString("pincode_of_reference_in_india"),
				rs.getString("contact_mobile_no_in_india"), rs.getString("contact_phone_no_in_india"),
				rs.getString("contact_mobile_no_permanently_residing"),
				rs.getString("contact_phone_no_permanently_residing"), rs.getString("employed_in_india"),
				rs.getString("spl_category_code"), fetchSplCatDesc(rs.getString("spl_category_code")),
				rs.getString("purpose_of_visit"), rs.getString("purpose_of_visitDesc"), rs.getString("frro_fro_code"),
				fetchImgForEdit(appid)));

	}

	@Override
	public String fetchUserIdAfterFinalSumbmit(String applicationId) throws Exception {

		String result = "";

		try {
			String query = "select entered_by from fr_formc_trans where form_c_appl_id=?";
			result = jdbcFormC.queryForObject(query, new Object[] { applicationId }, String.class);
			System.out.println("-------------USER-----------------" + result);
		} catch (Exception e) {
			throw new Exception("Invalid Application Id. Can't proceed.");
		}

		return result;

	}

	private String fetchGenderDesc(String genderCode) {
		if (genderCode == null) {
			return null;
		} else {
			String query = "select gender_desc from fr_gender where gender_code=?";
			return jdbcFormC.queryForObject(query, new Object[] { genderCode }, String.class);

		}

	}

	private String fetchCountryDesc(String countryCode) {
		if (countryCode == null) {
			return null;
		} else {
			String query = "select country_name from fr_country where country_code=?";
			return jdbcFormC.queryForObject(query, new Object[] { countryCode }, String.class);

		}

	}

	private String fetchStateDesc(String stateCode) {
		if (stateCode == null) {
			return null;
		} else {
			String query = "select state_name from fr_states where state_code=?";
			return jdbcFormC.queryForObject(query, new Object[] { stateCode }, String.class);

		}

	}
	private String fetchVisaTypeDesc(String visaType) {
		if (visaType == null) {
			return null;
		} else {
			String query = "select visatype_desc from fr_visa_type where visatype_id=?";
			return jdbcFormC.queryForObject(query, new Object[] { visaType }, String.class);

		}

	}
	private String fetchVisaSubtypeTypeDesc(String visaSubType) {
		if (visaSubType == null) {
			return null;
		} else {
			String query = "select purpose from fr_visa_purpose where purposecode=?";
			return jdbcFormC.queryForObject(query, new Object[] { visaSubType }, String.class);

		}

	}
	private String fetchCityDistDesc(String cityDist,String state) {
		if (cityDist == null) {
			return null;
		} else {
			String query = "select city_district_name from city_district where city_district_code=? and state_code=?";
			return jdbcFormC.queryForObject(query, new Object[] { cityDist,state }, String.class);

		}

	}

	private String fetchSplCatDesc(String splCatCode) {
		if (splCatCode == null) {
			return null;
		} else {
			String q = "select spl_category_desc from fr_formc_spl_category where spl_category_code=?";
			return jdbcFormC.queryForObject(q, new Object[] { splCatCode }, String.class);
		}
	}

	@Override
	public List<ApplicationList> fetchPendingApplicationList(String pass, String nat, String userid) throws Exception {

		StringBuffer sb = new StringBuffer("");

		Object[] paraObj = null;

		if (pass.equalsIgnoreCase("null"))
			pass = "";
		if (nat.equalsIgnoreCase("null"))
			nat = "";
		if (!pass.equals("") && !nat.equals("")) {
			sb.append("and passport_no=? and country_outside_india=? ");
			paraObj = new Object[] { userid, pass, nat };
		} else if (!pass.equals("")) {
			sb.append("and passport_no=?");
			paraObj = new Object[] { userid, pass };
		} else if (!nat.equals("")) {
			sb.append("and country_outside_india=?");
			paraObj = new Object[] { userid, nat };
		} else {
			paraObj = new Object[] { userid };
		}
		return jdbcFormC.query("SELECT form_c_appl_id, acco_code, surname, given_name,  "
				+ "(select country_name from fr_country where country_code= nationality) as nationalityDesc,nationality,country_outside_india,"
				+ "(select country_name from fr_country where country_code= country_outside_india) as country_outside_indiaDesc, passport_no,to_char(date_of_birth,'DD-MM-YYYY') as dob "
				+ " FROM fr_formc_trans_temp  where entered_by=?  " + sb, paraObj,
				(rs, rowNum) -> new ApplicationList(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("nationalityDesc"),
						rs.getString("country_outside_india"), rs.getString("country_outside_indiaDesc"),
						rs.getString("passport_no"),rs.getString("dob"),fetchImg(rs.getString("form_c_appl_id"))));
               
	}
	
	@Override
	public List<ApplicationList> fetchSubmittedApplicationList(String pass, String nat, String userid)
			throws Exception {

		StringBuffer sb = new StringBuffer("");

		Object[] paraObj = null;
		if (pass.equalsIgnoreCase("null"))
			pass = "";
		if (nat.equalsIgnoreCase("null"))
			nat = "";
		if (!pass.equals("") && !nat.equals("")) {
			sb.append("and passport_no=? and country_outside_india=? ");
			paraObj = new Object[] { userid, pass, nat };
		} else if (!pass.equals("")) {
			sb.append("and passport_no=?");
			paraObj = new Object[] { userid, pass };
		} else if (!nat.equals("")) {
			sb.append("and country_outside_india=?");
			paraObj = new Object[] { userid, nat };
		} else {
			paraObj = new Object[] { userid };
		}
		return jdbcFormC.query("SELECT form_c_appl_id, acco_code, surname, given_name,  "
				+ "(select country_name from fr_country where country_code= nationality) as nationalityDesc,nationality,country_outside_india,"
				+ "(select country_name from fr_country where country_code= country_outside_india) as country_outside_indiaDesc, passport_no,to_char(date_of_birth,'DD-MM-YYYY') as dob "
				+ " FROM fr_formc_trans where entered_by=?  " + sb, paraObj,
				(rs, rowNum) -> new ApplicationList(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("nationalityDesc"),
						rs.getString("country_outside_india"), rs.getString("country_outside_indiaDesc"),
						rs.getString("passport_no"),rs.getString("dob"),fetchImg(rs.getString("form_c_appl_id"))));

	}

}