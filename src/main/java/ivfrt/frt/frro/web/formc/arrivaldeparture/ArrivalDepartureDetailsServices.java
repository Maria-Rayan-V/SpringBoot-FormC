package ivfrt.frt.frro.web.formc.arrivaldeparture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.service.imagesave.ImageSave;
import ivfrt.frt.frro.web.service.imagesave.ImageSaveRepository;

@Repository
public class ArrivalDepartureDetailsServices implements ArrivalDepartureDetailsRepository {

	@Autowired
	@Qualifier("jdbcFormC")
	private JdbcTemplate jdbcFormC;

	@Autowired
	ImageSaveRepository imagerepo;

	@Override
	public DepartureDetails insertDepartDetail(DepartureDetails departdetail) {

		String query = "INSERT INTO fr_formc_departure("
				+ "	form_c_appl_id, date_of_departure, time_of_departure, frro_fro_code, "
				+ " entered_by, entered_on, departure_remark) " + "	VALUES (?, ?, ?, ?, ?, now(), ?)";

		int result = jdbcFormC.update(query, departdetail.getForm_c_appl_id(), departdetail.getDate_of_departure(),
				departdetail.getTime_of_departure(), departdetail.getFrro_fro_code(), departdetail.getEntered_by()
				, departdetail.getDeparture_remark());
		if (result > 0) {
			departdetail.setOperation_status_remark("Successfully inserted!");
			departdetail.setOperation_status(true);
		} else {

			departdetail.setOperation_status_remark("Some Unexpected error.");
			departdetail.setOperation_status(false);

		}
		return departdetail;

	}

	@Override
	public List<ArrivalDetail> findArrivalDetail(ArrivalDetail arrivalDetail) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String form_c_appl_id_condn = "";
		StringBuffer query_condition = new StringBuffer("");
		boolean or_append = false;

		StringBuffer sb = new StringBuffer();

		if (arrivalDetail != null) {
			if (arrivalDetail.getForm_c_appl_id() != null && arrivalDetail.getForm_c_appl_id().equals("") == false) {
				form_c_appl_id_condn = "and form_c_appl_id= ?  ";
				sb.append(arrivalDetail.getForm_c_appl_id());
			} else {
				// query_condition.append(" passport_no like
				// %"+arrivalDetail.getPassport_no()+"% ");

				if (arrivalDetail.getNationality() != null && arrivalDetail.getNationality().equals("") == false) {
					query_condition.append(" nationality= ? ");
					sb.append(arrivalDetail.getNationality());
					or_append = true;
				}
				/*
				 * if(arrivalDetail.getPassport_no()!=null &&
				 * arrivalDetail.getPassport_no().equals("") == false) { if(or_append) {
				 * query_condition.append("or"); sb.append(","); }
				 * query_condition.append(" passport_no like %"+arrivalDetail.getPassport_no()
				 * +"% "); //sb.append("%"+arrivalDetail.getPassport_no()+"%");
				 * //or_append=true; }
				 */
				if (arrivalDetail.getVisa_no() != null && arrivalDetail.getVisa_no().equals("") == false) {
					if (or_append) {
						query_condition.append("or");
						sb.append(",");
					}
					query_condition.append(" visa_no= ? ");
					sb.append(arrivalDetail.getVisa_no());
					or_append = true;
				}
				if (arrivalDetail.getDate_of_arrival_in_hotel() != null
						&& arrivalDetail.getDate_of_arrival_in_hotel().equals("") == false) {
					if (or_append) {
						query_condition.append("or");
						sb.append(",");
					}
					query_condition.append(" TO_CHAR(date_of_arrival_in_hotel :: DATE,'dd-mm-yyyy')=?");
					sb.append(formatter.format(arrivalDetail.getDate_of_arrival_in_hotel()));
				}
			}
		}
		System.out.println(query_condition.toString().trim().equals(""));
		if (query_condition.toString().trim().equals("")) {
			query_condition.append("1=1");
		}
		// String strDate= formatter.format(date);
		String query = "select form_c_appl_id,given_name,surname, passport_no,visa_no,date_of_arrival_in_hotel, time_of_arrival_in_hotel, "
				+ "Nationality,(select nm.COUNTRY_NAME from fr_country nm where nm.COUNTRY_CODE=Nationality) as nationality_name, "
				+ " frro_fro_code,acco_code  from fr_formc_trans  where acco_code= ? and frro_fro_code= ? and passport_no like '%"
				+ arrivalDetail.getPassport_no() + "%' " + form_c_appl_id_condn + "  and (" + query_condition
				+ ") and form_c_appl_id  not in (select form_c_appl_id from  fr_formc_departure where frro_fro_code= ? )";

		System.out.println("-------------" + query);
		System.out.println("---Acco Code----------" + arrivalDetail.getAcco_code());
		System.out.println("---FRRO  Code----------" + arrivalDetail.getFrro_fro_code());

		String parameterString = sb.toString();
		List<ArrivalDetail> arrivalDetails = new ArrayList<ArrivalDetail>();
		if (parameterString != null && parameterString.trim().equals("") == false) {
			arrivalDetails = jdbcFormC.query(query,
					new Object[] { arrivalDetail.getAcco_code(), arrivalDetail.getFrro_fro_code(), parameterString,
							arrivalDetail.getFrro_fro_code() },
					(rs, rowNum) -> new ArrivalDetail(rs.getString("given_name") + " " + rs.getString("surname"),
							rs.getString("form_c_appl_id"), rs.getString("frro_fro_code"), rs.getString("nationality"),
							rs.getString("passport_no"), rs.getString("visa_no"),
							rs.getDate("date_of_arrival_in_hotel"),
							// formatter.format(),
							rs.getString("time_of_arrival_in_hotel"), rs.getString("acco_code"),
							rs.getString("nationality_name"), fetchImg(rs.getString("form_c_appl_id"))

					));

		} else {
			arrivalDetails = jdbcFormC.query(query,
					new Object[] { arrivalDetail.getAcco_code(), arrivalDetail.getFrro_fro_code(),
							arrivalDetail.getFrro_fro_code() },
					(rs, rowNum) -> new ArrivalDetail(rs.getString("name"), rs.getString("form_c_appl_id"),
							rs.getString("frro_fro_code"), rs.getString("nationality"), rs.getString("passport_no"),
							rs.getString("visa_no"), rs.getDate("date_of_arrival_in_hotel"),
							// formatter.format(),
							rs.getString("time_of_arrival_in_hotel"), rs.getString("acco_code"),
							rs.getString("nationality_name"), fetchImg(rs.getString("form_c_appl_id"))

					));
		}
		return arrivalDetails;
	}

	private String fetchImg(String appid) {
		String response = "";
		ImageSave img = imagerepo.downLoadImage(appid);
		if (img != null) {
			response = Base64.encodeBase64String(img.getImgData());
		}
		return response;
	}

}
