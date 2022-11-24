package ivfrt.frt.frro.web.formc.arrivaldeparture;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DepartureDetails {

	private String form_c_appl_id;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date_of_departure;
	private String time_of_departure;
	private String frro_fro_code;
	private String entered_by;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date entered_on;
	private String departure_remark;
	private String operation_status_remark;
	private boolean operation_status;
	private String accoCode;

	public DepartureDetails() {
		super();
	}

	public DepartureDetails(String form_c_appl_id, Date date_of_departure, String time_of_departure,
			String frro_fro_code, String entered_by, Date entered_on, String departure_remark) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.date_of_departure = date_of_departure;
		this.time_of_departure = time_of_departure;
		this.frro_fro_code = frro_fro_code;
		this.entered_by = entered_by;
		this.entered_on = entered_on;
		this.departure_remark = departure_remark;
	}

	public DepartureDetails(String form_c_appl_id, Date date_of_departure, String time_of_departure,
			String frro_fro_code, String entered_by, Date entered_on, String departure_remark,
			String operation_status_remark, boolean operation_status, String accoCode) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.date_of_departure = date_of_departure;
		this.time_of_departure = time_of_departure;
		this.frro_fro_code = frro_fro_code;
		this.entered_by = entered_by;
		this.entered_on = entered_on;
		this.departure_remark = departure_remark;
		this.operation_status_remark = operation_status_remark;
		this.operation_status = operation_status;
		this.accoCode = accoCode;
	}

	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public Date getDate_of_departure() {
		return date_of_departure;
	}

	public void setDate_of_departure(Date date_of_departure) {
		this.date_of_departure = date_of_departure;
	}

	public String getTime_of_departure() {
		return time_of_departure;
	}

	public void setTime_of_departure(String time_of_departure) {
		this.time_of_departure = time_of_departure;
	}

	public String getFrro_fro_code() {
		return frro_fro_code;
	}

	public void setFrro_fro_code(String frro_fro_code) {
		this.frro_fro_code = frro_fro_code;
	}

	public String getEntered_by() {
		return entered_by;
	}

	public void setEntered_by(String entered_by) {
		this.entered_by = entered_by;
	}

	public Date getEntered_on() {
		return entered_on;
	}

	public void setEntered_on(Date entered_on) {
		this.entered_on = entered_on;
	}

	public String getDeparture_remark() {
		return departure_remark;
	}

	public void setDeparture_remark(String departure_remark) {
		this.departure_remark = departure_remark;
	}

	public boolean isOperation_status() {
		return operation_status;
	}

	public void setOperation_status(boolean operation_status) {
		this.operation_status = operation_status;
	}

	public String getAccoCode() {
		return accoCode;
	}

	public void setAccoCode(String accoCode) {
		this.accoCode = accoCode;
	}

	public String getOperation_status_remark() {
		return operation_status_remark;
	}

	public void setOperation_status_remark(String operation_status_remark) {
		this.operation_status_remark = operation_status_remark;
	}

}
