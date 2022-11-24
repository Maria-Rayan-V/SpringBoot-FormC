package ivfrt.frt.frro.web.formc.tracking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FormC_TrackingDetailsServices implements FormC_TrackingDetailsRepositry {

	@Autowired
	@Qualifier("jdbcFormC")
	private JdbcTemplate jdbcFormC;
	
	
	@Override
	public List<FormC_TrackingDetails> fetchTempApplicationDetails(String accoCode, String frroFroCode) {

		
		String query="select form_c_appl_id,given_name,  surname,(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=Nationality) as  nationality,passport_no,to_char(date_of_birth,'dd/mm/yyyy') as  date_of_birth  "
				+ " from fr_formc_trans_temp where  acco_code=?  and  frro_fro_code=? order by entered_on desc";
		
		return jdbcFormC.query(query, new Object[] { accoCode, frroFroCode },
				(rs, rowNum) -> new FormC_TrackingDetails(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("passport_no"),
						rs.getString("date_of_birth")));
	
	}


	@Override
	public List<FormC_TrackingDetails> fetchCheckOutPendingsApplicationDetails(String accoCode, String frroFroCode) {
		String query="select form_c_appl_id,given_name,  surname,(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=Nationality) as  nationality,passport_no,to_char(date_of_birth,'dd/mm/yyyy') as  date_of_birth  "
				+ " from fr_formc_trans where  acco_code=?  and  frro_fro_code=? "
				+ " and form_c_appl_id  not in (select form_c_appl_id from  fr_formc_departure where frro_fro_code= ? )  order by entered_on desc";
		
		return jdbcFormC.query(query, new Object[] { accoCode, frroFroCode,frroFroCode },
				(rs, rowNum) -> new FormC_TrackingDetails(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("passport_no"),
						rs.getString("date_of_birth")));
	}


	@Override
	public List<DepartDetailsPdf> fetchDeparturedApplicationDetails(String accoCode, String frroFroCode) {
		

		/*
		 * String
		 * query="select form_c_appl_id,given_name,  surname,(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=Nationality) as  nationality,passport_no,to_char(date_of_birth,'dd/mm/yyyy') as  date_of_birth "
		 * + " from fr_formc_trans where  acco_code=?  and  frro_fro_code=? " +
		 * " and form_c_appl_id  in (select form_c_appl_id from  fr_formc_departure where frro_fro_code= ? ) "
		 * ;
		 */
		
		String query="select a.form_c_appl_id,a.given_name,  a.surname,(select nm.COUNTRY_NAME from fr_country nm  "
				+ " where nm.COUNTRY_CODE=a.Nationality) as  nationality,a.passport_no, "
				+ " to_char(a.date_of_birth,'dd/mm/yyyy') as  date_of_birth,to_char(b.date_of_departure,'dd/mm/yyyy') as date_of_departure,b.time_of_departure, "
				+ " b.departure_remark,to_char(b.entered_on,'dd/mm-yyyy') as entered_on "
				+ " from fr_formc_trans a left join fr_formc_departure b on a.form_c_appl_id=b.form_c_appl_id "
				+ " where  a.acco_code=?  and  a.frro_fro_code=? order by b.date_of_departure desc ";
		
		/*
		 * return jdbcFormC.query(query, new Object[] { accoCode, frroFroCode }, (rs,
		 * rowNum) -> new DepartDetailsPdf(rs.getString("form_c_appl_id"),
		 * rs.getString("given_name"), rs.getString("surname"),
		 * rs.getString("nationality"), rs.getString("passport_no"),
		 * rs.getString("date_of_birth")));
		 */
		return jdbcFormC.query(query, new Object[] { accoCode, frroFroCode},
				(rs, rowNum) -> new DepartDetailsPdf(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("passport_no"),
						rs.getString("date_of_birth"),rs.getString("date_of_departure")
						,rs.getString("time_of_departure"),rs.getString("entered_on"),rs.getString("departure_remark")));
	
	}


	@Override
	public List<DepartDetailsPdf> exportPdfOfDepartDetails(String applicationid) {
		
	/*	String sql="select a.form_c_appl_id,a.given_name,  a.surname,"
				+ "(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=a.Nationality) as  nationality, "
				+ "a.passport_no,to_char(a.date_of_birth,'dd/mm/yyyy') as date_of_birth,to_char(b.date_of_departure,'dd/mm/yyyy') as date_of_departure,b.time_of_departure,b.departure_remark,to_char(b.entered_on,'dd/mm/yyyy') as entered_on "
				+ " from fr_formc_trans a left join fr_formc_departure b on a.form_c_appl_id=b.form_c_appl_id "
				+ " where a.form_c_appl_id=? "; */
		
	String sql="select a.form_c_appl_id,a.given_name,  a.surname, "
			+ "(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=a.Nationality) as  nationality, "
			+ "a.passport_no,to_char(a.date_of_birth,'dd/mm/yyyy') as date_of_birth, "
			+ "to_char(b.date_of_departure,'dd/mm/yyyy') as date_of_departure,b.time_of_departure,b.departure_remark, "
			+ "to_char(b.entered_on,'dd/mm/yyyy') as entered_on, c.acco_code, "
			+ "c.acco_name ||', ' || acco_address  ||', ' || (select city_district_name from city_district where city_district_code=c.city_district "
			+ " and state_code=c.state_code)||', '||(select state_name from fr_states where state_code=c.state_code ) ||', Email id : ' "
			+ " || email_id ||', Contact no. : '||phone_no ||','|| mobile_no  as accom_address "
			+ "from fr_formc_trans a left join fr_formc_departure b on a.form_c_appl_id=b.form_c_appl_id "
			+ "LEFT JOIN fr_accomodator c on a.acco_code= c.acco_code and a.frro_fro_code=c.frro_fro_code "
			+ " where a.form_c_appl_id=? ";
		
		return jdbcFormC.query(sql, new Object[] {applicationid},
				(rs, rowNum) -> new DepartDetailsPdf(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"), rs.getString("nationality"), rs.getString("passport_no"),
						rs.getString("date_of_birth"),rs.getString("date_of_departure")
						,rs.getString("time_of_departure"),rs.getString("entered_on"),rs.getString("departure_remark"),rs.getString("accom_address")));
	}

	
	
}
