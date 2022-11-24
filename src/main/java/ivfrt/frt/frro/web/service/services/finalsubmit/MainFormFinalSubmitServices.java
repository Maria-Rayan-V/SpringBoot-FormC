package ivfrt.frt.frro.web.service.services.finalsubmit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MainFormFinalSubmitServices  implements MainFormFinalSubmitRepository{

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbcEservices;
	
	
	
	@Override
	@Transactional
	public MainFormFinalSubmit finalSubmit(MainFormFinalSubmit mainFormFinalSubmit) throws Exception{
		
		validateDataFinalSubmission(mainFormFinalSubmit);
		
		
		dataTransfer_fr_applicant_details_temp(mainFormFinalSubmit.getApplication_id());
		dataTransfer_fr_applicant_details_supp_temp(mainFormFinalSubmit.getApplication_id());
		dataTransfer_fr_applicant_doc_temp(mainFormFinalSubmit.getApplication_id());
		dataTransfer_fr_previous_registration_dtls_temp(mainFormFinalSubmit.getApplication_id());
		dataTransfer_fr_accomp_mem_dtls_temp(mainFormFinalSubmit.getApplication_id());
		
		dataTransfer_Service_Details(mainFormFinalSubmit);
		
		//After detlete data from accompying,prv reg detail,doc_and sub temp delete data from  fr_applicant_details_temp 
	    deleteDataFromTemp_fr_applicant_details_temp(mainFormFinalSubmit.getApplication_id());
		mainFormFinalSubmit.setStatus("Successfully submitted.");
		return mainFormFinalSubmit;
	}
	
	
	
		private void validateDataFinalSubmission(MainFormFinalSubmit mainFormFinalSubmit) throws Exception {
			
			int upload_count=jdbcEservices.queryForObject("select count(*) from fr_applicant_doc_temp "
					+ " where application_id=?  and doc_type_code!='999'",new Object[] {mainFormFinalSubmit.getApplication_id()}, Integer.class);
			int required_doc_count=jdbcEservices.queryForObject("select count(*) from e_app_doc_temp "
					+ " where application_id=? and doc_id not in('999')",new Object[] {mainFormFinalSubmit.getApplication_id()}, Integer.class);
			
			if(!(upload_count==required_doc_count)  ) {
				throw new Exception("Please upload all required documents.");
			}else {
				String q="UPDATE fr_applicant_details_temp SET web_stage=? WHERE application_id=? ";
				 jdbcEservices.update(q,7,mainFormFinalSubmit.getApplication_id());
			}
			
			
			//Check for atleast 1 documents
			
			//int upload_count_photo=jdbc.queryForObject("select count(*) from fr_applicant_doc_temp "
			//		+ " where application_id=? and doc_type_code='1'",new Object[] {mainFormFinalSubmit.getApplication_id()}, Integer.class);
		
			
			//int upload_count=jdbc.queryForObject("select count(*) from fr_applicant_doc_temp "
			//		+ " where application_id=? ",new Object[] {mainFormFinalSubmit.getApplication_id()}, Integer.class);
		
			//int count=upload_count_photo+upload_count;
	
			/*
			 * if(upload_count_photo==0) { throw new
			 * Exception("Please upload required documents."); }
			 */
			
			String q="UPDATE fr_applicant_details_temp SET web_stage=? WHERE application_id=? ";
			 jdbcEservices.update(q,7,mainFormFinalSubmit.getApplication_id());
			
			
			
			String query="select frro_fro_code from fr_applicant_details_temp where application_id =?";
			String  frroCode= jdbcEservices.queryForObject(query,new Object[] {mainFormFinalSubmit.getApplication_id()}, String.class);
	
			if(frroCode==null || frroCode.equalsIgnoreCase("")) {
				throw new Exception("Frro/Fro code required.");
			}
			
			String query1="select form_type from fr_applicant_details_supp_temp where application_id =?";
			String  formType= jdbcEservices.queryForObject(query1,new Object[] {mainFormFinalSubmit.getApplication_id()}, String.class);
	
			if(formType==null || formType.equals("") ) {
				throw new Exception("Service id required.");
			}
		
		}



		private void deleteDataFromTemp_fr_applicant_details_temp(String application_id) {


			
		

			String q2="delete from fr_applicant_doc_temp where application_id=? ";
			jdbcEservices.update(q2, application_id);
			String q3="delete from e_app_doc_temp where application_id=? ";
			jdbcEservices.update(q3, application_id);
		

			String q4="delete from fr_previous_registration_dtls_temp where application_id=? ";
			jdbcEservices.update(q4, application_id);
		
			

			String q5="delete from fr_accomp_mem_dtls_temp where application_id=? ";
			jdbcEservices.update(q5, application_id);
		
			

			String q6="delete from fr_appl_dtls_add_places_temp where application_id=? ";
			jdbcEservices.update(q6, application_id);

			String q7="delete from fr_rp_area_permit_temp where application_id=? ";
			jdbcEservices.update(q7, application_id);
		

			String q8="delete from efr_chan_of_course_temp where application_id=? ";
			jdbcEservices.update(q8, application_id);
		
		
			String q1="delete from fr_applicant_details_supp_temp where application_id=? ";
			jdbcEservices.update(q1, application_id);
			
			String q="delete from fr_applicant_details_temp where application_id=? ";
			jdbcEservices.update(q, application_id);
	}



		private void dataTransfer_Service_Details(MainFormFinalSubmit mainFormFinalSubmit) {
		
			String serviceId []=mainFormFinalSubmit.getServiceids();
			
			if(serviceId!=null && serviceId.length>0) {
				
				for (String serId : serviceId) {
					
					if(serId.equals("10")) {
						String query="INSERT INTO fr_appl_dtls_add_places(" + 
								"	application_id, srl_no, place, city_district, state_code, reason_of_visit, entered_by) " + 
								
								"	select application_id, srl_no, place, city_district, state_code, reason_of_visit, entered_by "
								+ " from fr_appl_dtls_add_places_temp where application_id=? ";
						int status=jdbcEservices.update(query,mainFormFinalSubmit.getApplication_id());
						if (status > 0) {
							/*
							 * String q="delete from fr_appl_dtls_add_places_temp where application_id=? ";
							 * jdbc.update(q, mainFormFinalSubmit.getApplication_id());
							 */}
						//fr_appl_dtls_add_places_temp
					}
					if(serId.equals("13")) {
						

						String query="INSERT INTO fr_rp_area_permit( " + 
								"	application_id, rp_state, rp_city_district, rp_place, rp_permission_for_days, rp_valid_from, "
								+ " rp_valid_to, rp_purpose, entered_by) " + 
								
								"	select application_id, rp_state, rp_city_district, rp_place, rp_permission_for_days, "
								+ " rp_valid_from,rp_valid_to, rp_purpose, entered_by"
								+ " from fr_rp_area_permit_temp where application_id=? ";
						int status=jdbcEservices.update(query,mainFormFinalSubmit.getApplication_id());
						if (status > 0) {
							/*
							 * String q="delete from fr_rp_area_permit_temp where application_id=? ";
							 * jdbc.update(q, mainFormFinalSubmit.getApplication_id());
							 */}
						
						//fr_rp_area_permit_temp
					}
					if(serId.equals("19")) {
						
						String query="INSERT INTO efr_chan_of_course(" + 
								"	application_id, srl_no, course_name, course_supp, course_enrol_year, course_compl_year, entered_by)" + 
								
								" select application_id, srl_no, course_name, course_supp, course_enrol_year, course_compl_year, entered_by"
								+ " from efr_chan_of_course_temp where application_id=? ";
						int status=jdbcEservices.update(query,mainFormFinalSubmit.getApplication_id());
						if (status > 0) {
							/*
							 * String q="delete from efr_chan_of_course_temp where application_id=? ";
							 * jdbc.update(q, mainFormFinalSubmit.getApplication_id());
							 */}
						
						
						//efr_chan_of_course_temp
					}
					
				}
			}
		
	}



		private void dataTransfer_fr_accomp_mem_dtls_temp(String application_id) {
		String query="INSERT INTO fr_accomp_mem_dtls( " + 
				"	application_id, accomp_srno, accomp_name, accomp_nationality, accomp_pasport_no, accomp_relation,  " + 
				"	entered_by, client_from, gender_code, age, occupation_code) " + 
				
				"	select application_id, accomp_srno, accomp_name, accomp_nationality, accomp_pasport_no, accomp_relation, " + 
				"	entered_by, client_from, gender_code, age, occupation_code  " + 
				"	from fr_accomp_mem_dtls_temp where application_id=?";
		
			int status=jdbcEservices.update(query,application_id);
			if (status > 0) {
				/*
				 * String q="delete from fr_accomp_mem_dtls_temp where application_id=? ";
				 * jdbc.update(q, application_id);
				 */}
		}

		private void dataTransfer_fr_previous_registration_dtls_temp(String application_id) {
		
			String query="insert into fr_previous_registration_dtls (application_id, previous_reg_srno, frro_fro_code_previous_regn, " + 
					"										  previous_regn_from_date, previous_regn_to_date, entered_by, " + 
					"										   client_from, previous_regn_number,  " + 
					"										  visa_type_previous_regn)  "
					+ " select application_id, previous_reg_srno, frro_fro_code_previous_regn, " + 
					"										  previous_regn_from_date, previous_regn_to_date, entered_by, " + 
					"										   client_from, previous_regn_number, " + 
					"										  visa_type_previous_regn from fr_previous_registration_dtls_temp " + 
					"										  where application_id=? ";
			int status=jdbcEservices.update(query,application_id);
			
			if (status > 0) {
				/*
				 * String
				 * q="delete from fr_previous_registration_dtls_temp where application_id=? ";
				 * jdbc.update(q, application_id);
				 */}
			
			}
		private void dataTransfer_fr_applicant_doc_temp(String application_id) throws Exception {
			
			

			String q_doc="insert into fr_applicant_doc " + 
			" (srno, application_id, entered_by,  client_from, docpdf, doc_type_code, cnf_flag) " + 
			" select srno, application_id, entered_by,  client_from, docpdf, doc_type_code, cnf_flag " + 
			" from fr_applicant_doc_temp where application_id=? " ;
			int status=jdbcEservices.update(q_doc,application_id);
	
				
			if (status > 0) {
				/*
				 * String q="delete from fr_applicant_doc_temp where application_id=? ";
				 * jdbc.update(q, application_id); String
				 * q1="delete from e_app_doc_temp where application_id=? "; jdbc.update(q1,
				 * application_id);
				 */}

		
	}
//srno, application_id, entered_by, entered_on, client_from, docpdf, doc_type_code, cnf_flag
	
	private void dataTransfer_fr_applicant_details_supp_temp(String application_id) {
	
		String query_supp="INSERT INTO fr_applicant_details_supp (application_id, martial_status, passport_country_of_issue, visa_country_of_issue, whether_previously_in_india_extn, refused_extn, granted_extn, current_reg_no, current_extn_no, extension_period_year, extension_period_month, extension_perod_days, reason_for_extn, missonary_nature_of_work, form_type, new_born_child_flag, rv_countrycode, rv_journeypurpose, rv_staymonths, rv_stayfromdate, rv_staytodate, exit_dept_date, exit_in_days, exit_delay_reason, cur_reg_flag, cur_ext_flg, reason_of_chang_addr, chang_mod_of_jou, chang_mod_dep_port, reason_of_chang_mod_jou, con_visa_type, con_valid_for_no_of_entry, con_for_period_year, con_for_period_month, con_for_period_days, reason_for_con, chang_dep_port, reason_of_chang_port, cur_reg_valid_upto, rex_for_period_year, rex_for_period_month, rex_for_period_days, reason_for_rex, reason_of_chngofppt, passport_surrender, new_visa_type, new_visa_for_period_year, new_visa_for_period_month, new_visa_for_period_days, reason_for_new_visa, registration_sub_category, visaconversion_sub_category, surrogacy_flag, religion_type, religion_others, establishment_id, employee_epfo_id, ltv_flag, date_of_expiry_auth_period, visa_assignee_no, applicant_type, protocol_id_flag, exit_port, previous_passport_no, prev_visa_no, lost_passport_number, lost_passport_issue_date, lost_passport_issue_place, lost_passport_expiry_date, fir_number, police_station_name, place, city_district, state_code, embassy_letter_no, embassy_letter_date, embassy_name, embassy_place, embassy_city, embassy_state, exit_permit_flag, ticket_number, lost_damage_flag, ltv_category_code, permission_refusal_flag, permission_refusal_date, passport_required_flag, minor_stay_in_india_flag, marital_status_code, husband_nationality, earlier_marriage_flag, earlier_date_of_marriage, earlier_spouse_name, earlier_spouse_address, earlier_spouse_city, earlier_spouse_country, earlier_marital_status_code, ind_citizen_before_marriage, male_support_flag, settled_kerla_before_partition, pcc_purpose, pcc_fromdate, pcc_todate, aadhar_no, father_nationlity, mother_nationlity, caste, sub_caste, reason_of_migration, native_police_case, police_case_in_india, native_country_visit, indian_citizenship_flag, citizenship_details, occupation_flag, ltv_category_flag, date_of_migration, sform_reg_no, inst_name, inst_address, inst_state, inst_city_dist, inst_pincode, inst_email, inst_phone, conversion_type_flag, nrt_flag, nrt_exit_dept_date, nrt_exit_delay_reason, nrt_exit_port, nrt_previous_visa_extn_flag, nrt_previous_extn_granted, nrt_previous_extn_upto, nrt_extn_year, nrt_extn_month, nrt_extn_days, nrt_extn_reason, tlp_service_no, tlp_expiry_date, tlp_extn_req_upto, tlp_reason_extn, visa_purpose_code, question_id, userid, phone_outside_india, doc_name, doc_address, doc_state, doc_city, doc_reg_number, medical_permit_from, medical_permit_to, medical_permit_reason, ltv_service_number, ltv_ext_reason, ltv_ext_upto, transfer_state, transfer_city_district, transfer_reason, transfer_frro_fro_code, transfer_frro_fro_desc_code, pio_oci_card_hold_flag, permit_purpose, pio_oci_type, pio_oci_relaxation_type, pio_oci_number, pio_oci_issuedate, pio_oci_expirydate, pio_oci_issueplace, rc_rp_number, cfro_app_id, ic_number, ic_expiry_date, exit_other_reason" + 
				") select "
				+ " application_id, martial_status, passport_country_of_issue, visa_country_of_issue, whether_previously_in_india_extn, refused_extn, granted_extn, current_reg_no, current_extn_no, extension_period_year, extension_period_month, extension_perod_days, reason_for_extn, missonary_nature_of_work, form_type, new_born_child_flag, rv_countrycode, rv_journeypurpose, rv_staymonths, rv_stayfromdate, rv_staytodate, exit_dept_date, exit_in_days, exit_delay_reason, cur_reg_flag, cur_ext_flg, reason_of_chang_addr, chang_mod_of_jou, chang_mod_dep_port, reason_of_chang_mod_jou, con_visa_type, con_valid_for_no_of_entry, con_for_period_year, con_for_period_month, con_for_period_days, reason_for_con, chang_dep_port, reason_of_chang_port, cur_reg_valid_upto, rex_for_period_year, rex_for_period_month, rex_for_period_days, reason_for_rex, reason_of_chngofppt, passport_surrender, new_visa_type, new_visa_for_period_year, new_visa_for_period_month, new_visa_for_period_days, reason_for_new_visa, registration_sub_category, visaconversion_sub_category, surrogacy_flag, religion_type, religion_others, establishment_id, employee_epfo_id, ltv_flag, date_of_expiry_auth_period, visa_assignee_no, applicant_type, protocol_id_flag, exit_port, previous_passport_no, prev_visa_no, lost_passport_number, lost_passport_issue_date, lost_passport_issue_place, lost_passport_expiry_date, fir_number, police_station_name, place, city_district, state_code, embassy_letter_no, embassy_letter_date, embassy_name, embassy_place, embassy_city, embassy_state, exit_permit_flag, ticket_number, lost_damage_flag, ltv_category_code, permission_refusal_flag, permission_refusal_date, passport_required_flag, minor_stay_in_india_flag, marital_status_code, husband_nationality, earlier_marriage_flag, earlier_date_of_marriage, earlier_spouse_name, earlier_spouse_address, earlier_spouse_city, earlier_spouse_country, earlier_marital_status_code, ind_citizen_before_marriage, male_support_flag, settled_kerla_before_partition, pcc_purpose, pcc_fromdate, pcc_todate, aadhar_no, father_nationlity, mother_nationlity, caste, sub_caste, reason_of_migration, native_police_case, police_case_in_india, native_country_visit, indian_citizenship_flag, citizenship_details, occupation_flag, ltv_category_flag, date_of_migration, sform_reg_no, inst_name, inst_address, inst_state, inst_city_dist, inst_pincode, inst_email, inst_phone, conversion_type_flag, nrt_flag, nrt_exit_dept_date, nrt_exit_delay_reason, nrt_exit_port, nrt_previous_visa_extn_flag, nrt_previous_extn_granted, nrt_previous_extn_upto, nrt_extn_year, nrt_extn_month, nrt_extn_days, nrt_extn_reason, tlp_service_no, tlp_expiry_date, tlp_extn_req_upto, tlp_reason_extn, visa_purpose_code, question_id, userid, phone_outside_india, doc_name, doc_address, doc_state, doc_city, doc_reg_number, medical_permit_from, medical_permit_to, medical_permit_reason, ltv_service_number, ltv_ext_reason, ltv_ext_upto, transfer_state, transfer_city_district, transfer_reason, transfer_frro_fro_code, transfer_frro_fro_desc_code, pio_oci_card_hold_flag, permit_purpose, pio_oci_type, pio_oci_relaxation_type, pio_oci_number, pio_oci_issuedate, pio_oci_expirydate, pio_oci_issueplace, rc_rp_number, cfro_app_id, ic_number, ic_expiry_date, exit_other_reason " + 
				"	from 	fr_applicant_details_supp_temp where application_id=?  ";
		int status=jdbcEservices.update(query_supp,application_id);
		
		if (status > 0) {
			/*
			 * String
			 * q="delete from fr_applicant_details_supp_temp where application_id=? ";
			 * jdbc.update(q, application_id);
			 */}
		
	}

	private void dataTransfer_fr_applicant_details_temp(String application_id) {
		String query="INSERT INTO fr_applicant_details(" + 
				" 	application_id, surname, given_name, father_name, mother_name, spouse_name, gender, height, " + 
				"	other_identification, dob_dateformat, date_of_birth, age, place_of_birth, birth_city, birth_country,  " + 
				"	present_nationality, previous_nationality, manner_acq_nalty, date_of_acq_present_nalty,  " + 
				"	dual_nationality_flag, dual_nationality, dual_ppt_no, dual_ppt_issue_date, dual_ppt_expire_date, " + 
				"	dual_traveled_india, indian_origin, address_outside_india, city_outside_india, country_outside_india, " + 
				"	address_in_india_longstay, city_district_in_india_longstay, state_in_india_longstay, " + 
				"	pincode_in_india_longstay, telephone_in_india_longstay, mobile_no_in_india_longstay, " + 
				"	address_in_india_any_other, city_district_in_india_any_other, state_in_india_any_other, " + 
				"	pincode_in_india_any_other, telephone_in_india_any_other, mobile_no_in_india_any_other, e_mail_id, " + 
				"	profession_occupation, passport_no, passport_date_of_issue, passport_place_of_issue, passport_expiry_date, " + 
				"	visa_no, visa_date_of_issue, visa_place_of_issue, visa_expiry_date, visa_type, visa_spl_endor, e_i_b_name, " + 
				"	e_i_b_address, e_i_b_city_district, e_i_b_state, e_i_b_phone, e_i_b_e_mail_id, valid_for_no_of_entry, " + 
				"	place_of_embarkation, city_of_embarkation, country_of_embarkation, date_of_arrival_in_india, " + 
				"	place_of_arrival_in_india, mode_of_journey, carrier_code, purpose_of_visit, accompany_flag, " + 
				"	emergency_contact_person_name, emergency_contact_relationship, emergency_contact_address, " + 
				"	emergency_contact_city, emergency_contact_country_code, emergency_contact_telephone, category, " + 
				"	served_in_military, organisation, designation, rank, place_of_last_posting, place_of_last_posting_country, " + 
				"	 entered_by,  client_from, frro_fro_code, " + 
				"	previous_regn_flag ) " +
				
				"	 select application_id, surname, given_name, father_name, mother_name, spouse_name, gender, height, " + 
				"	other_identification, dob_dateformat, date_of_birth, age, place_of_birth, birth_city, birth_country,  " + 
				"	present_nationality, previous_nationality, manner_acq_nalty, date_of_acq_present_nalty,  " + 
				"	dual_nationality_flag, dual_nationality, dual_ppt_no, dual_ppt_issue_date, dual_ppt_expire_date, " + 
				"	dual_traveled_india, indian_origin, address_outside_india, city_outside_india, country_outside_india, " + 
				"	address_in_india_longstay, city_district_in_india_longstay, state_in_india_longstay, " + 
				"	pincode_in_india_longstay, telephone_in_india_longstay, mobile_no_in_india_longstay, " + 
				"	address_in_india_any_other, city_district_in_india_any_other, state_in_india_any_other, " + 
				"	pincode_in_india_any_other, telephone_in_india_any_other, mobile_no_in_india_any_other, e_mail_id, " + 
				"	profession_occupation, passport_no, passport_date_of_issue, passport_place_of_issue, passport_expiry_date, " + 
				"	visa_no, visa_date_of_issue, visa_place_of_issue, visa_expiry_date, visa_type, visa_spl_endor, e_i_b_name, " + 
				"	e_i_b_address, e_i_b_city_district, e_i_b_state, e_i_b_phone, e_i_b_e_mail_id, valid_for_no_of_entry, " + 
				"	place_of_embarkation, city_of_embarkation, country_of_embarkation, date_of_arrival_in_india, " + 
				"	place_of_arrival_in_india, mode_of_journey, carrier_code, purpose_of_visit, accompany_flag, " + 
				"	emergency_contact_person_name, emergency_contact_relationship, emergency_contact_address, " + 
				"	emergency_contact_city, emergency_contact_country_code, emergency_contact_telephone, category, " + 
				"	served_in_military, organisation, designation, rank, place_of_last_posting, place_of_last_posting_country, " + 
				"	entered_by,  client_from, frro_fro_code, " + 
				"	previous_regn_flag from fr_applicant_details_temp where application_id=? ";
		
		jdbcEservices.update(query,application_id);
		
		
	}



	@Override
	public String fetchUserId(String applicationId) throws Exception {

		String result="";

		try {
		String query="select userid from fr_applicant_details_supp_temp where application_id=?";
		 result= jdbcEservices.queryForObject(query,new Object[] {applicationId}, String.class);
		 System.out.println("-------------USER-----------------"+result);
		}catch (Exception e) {
			throw new Exception("Inavlid Application Id/User id.Can't proceed.");
		}
	
	  return result;
	}

}
