package ivfrt.frt.frro.web.formc.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.formc.tracking.DepartDetailsPdf;


@Repository
public class FormC_MisReportServices implements FormC_MisReportRepository {

	
	@Autowired
	 JdbcTemplate jdbcFormC;
	
	
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
	public List<FormC_MisReport> exportPdfOfArrivalDetailsList(FormC_MisReport reportParameter) {
		validateDateRangeGap(reportParameter.getFrom_date(),reportParameter.getTo_date(),30);
		String query="select form_c_appl_id,given_name,coalesce(surname,'') as surname,passport_no, "
				+ "(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=Nationality) as  nationality, "
				+ " to_char(date_of_arrival_in_hotel,'dd/mm/yyyy') as date_of_arrival_in_hotel,time_of_arrival_in_hotel,"
				+ " to_char(entered_on,'dd/mm/yyyy hh:mm') as entered_on "
				+ " from fr_formc_trans where   date(date_of_arrival_in_hotel) "
				+ " between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') "
				+ "and acco_code = ? and frro_fro_code = ? order by date_of_arrival_in_hotel";
		
		return jdbcFormC.query(query, new Object[] {reportParameter.getFrom_date(),reportParameter.getTo_date(),reportParameter.getAcco_code(),reportParameter.getFrro_fro_code()},
				(rs, rowNum) -> new FormC_MisReport(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"),rs.getString("passport_no"), rs.getString("nationality"), 
						rs.getString("date_of_arrival_in_hotel"),rs.getString("time_of_arrival_in_hotel"),rs.getString("entered_on")));
	}

	@Override
	public String fetchAccomDetails(String accom_code, String frro_fro_code) {

		String result = "";

		try {
			String query = "select b.acco_name||','||b.acco_address||','||c.city_district_name||','||d.state_name as accom_address "
					+ " from  fr_accomodator b, city_district c, fr_states d where b.acco_code = ? and b.frro_fro_code = ? "
					+ " and d.state_code=b.state_code and c.state_code=b.state_code "
					+ " and c.city_district_code=b.city_district and c.active='Y'";
			result = jdbcFormC.queryForObject(query, new Object[] { accom_code,frro_fro_code }, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/* to_char(date_of_arrival_in_hotel,'dd/MM/yyyy') as   to_char(b.entered_on,'dd/MM/yyyy') as  to_char(b.date_of_departure,'dd/MM/yyyy') as*/
	@Override
	public List<FormC_MisReport> exportPdfOfDepartureDetailsList(FormC_MisReport reqParameter) {
		
		validateDateRangeGap(reqParameter.getFrom_date(),reqParameter.getTo_date(),30);
		
		
		String query="select a.form_c_appl_id,a.given_name,  a.surname, "
				+ "(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=a.Nationality) as  nationality, "
				+ "a.passport_no,to_char(date_of_arrival_in_hotel,'dd/MM/yyyy') as date_of_arrival_in_hotel,a.time_of_arrival_in_hotel, "
				+ "  to_char(b.date_of_departure,'dd/MM/yyyy') as date_of_departure,b.time_of_departure,b.departure_remark, "
				+ " to_char(b.entered_on,'dd/MM/yyyy hh:mm') as  entered_on "
				+ " from fr_formc_trans a ,fr_formc_departure b where a.form_c_appl_id=b.form_c_appl_id "
				+ "and a.form_c_appl_id in (select form_c_appl_id  from fr_formc_departure) "
				+ "and a.acco_code=? and a.frro_fro_code=?  and b.date_of_departure between to_date(?,'dd/mm/yyyy') and to_date(?,'dd/mm/yyyy')";
		
		
		return jdbcFormC.query(query, new Object[] {reqParameter.getAcco_code(),reqParameter.getFrro_fro_code(),reqParameter.getFrom_date(),reqParameter.getTo_date()},
				(rs, rowNum) -> new FormC_MisReport(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"),rs.getString("passport_no"), rs.getString("nationality"), 
						rs.getString("date_of_arrival_in_hotel"),rs.getString("time_of_arrival_in_hotel"),rs.getString("entered_on"),rs.getString("date_of_departure"),rs.getString("time_of_departure"),rs.getString("departure_remark")));
	}

	private void validateDateRangeGap(String from_date, String to_date, int daysGap) {
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			 Date fromDate = sdf.parse(from_date);
	         Date toDate = sdf.parse(to_date);
	         
	         long difference_In_Time
             = toDate.getTime() -fromDate.getTime();
	         long diff = TimeUnit.DAYS.convert(difference_In_Time, TimeUnit.MILLISECONDS);
	        
	         
	         if(diff >daysGap) {
	        	 throw new ValidationException();
	         }
	         
		} 
		catch (ValidationException ve) {
			throw new ValidationException("Date range must be 30 days only.");
		}catch (Exception e) {
			throw new ValidationException("Please check date input.");
		}
	}

	@Override
	public List<FormC_MisReport> exportPdfOfArrivalDetailsListByEntredOn(@Valid FormC_MisReport reqParameter) {

		validateDateRangeGap(reqParameter.getFrom_date(),reqParameter.getTo_date(),30);
		String query="select form_c_appl_id,given_name,coalesce(surname,'') as surname,passport_no, "
				+ "(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=Nationality) as  nationality, "
				+ " to_char(date_of_arrival_in_hotel,'dd/mm/yyyy') as date_of_arrival_in_hotel,time_of_arrival_in_hotel,"
				+ " to_char(entered_on,'dd/mm/yyyy hh:mm') as entered_on "
				+ " from fr_formc_trans where   date(entered_on) "
				+ " between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') "
				+ "and acco_code = ? and frro_fro_code = ? order by date_of_arrival_in_hotel";
		
		return jdbcFormC.query(query, new Object[] {reqParameter.getFrom_date(),reqParameter.getTo_date(),reqParameter.getAcco_code(),reqParameter.getFrro_fro_code()},
				(rs, rowNum) -> new FormC_MisReport(rs.getString("form_c_appl_id"), rs.getString("given_name"),
						rs.getString("surname"),rs.getString("passport_no"), rs.getString("nationality"), 
						rs.getString("date_of_arrival_in_hotel"),rs.getString("time_of_arrival_in_hotel"),rs.getString("entered_on")));
	
	}

}
