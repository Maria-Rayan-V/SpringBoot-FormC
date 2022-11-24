package ivfrt.frt.frro.web.formc.tracking;

public class DepartDetailsPdf {

	private String form_c_appl_id;
	private String given_name;
	private String surname;
	private String nationality;
	private String passnum;
	private String dob;
	private String date_of_departure;
	private String time_of_departure;
	private String entered_on;
	private String departure_remark;
	private String accom_details;
	private String from_date;
	private String to_date;
	
	
	
	
	
	public DepartDetailsPdf(String form_c_appl_id, String given_name, String surname, String nationality,
			String passnum, String dob, String date_of_departure, String time_of_departure, String entered_on,
			String departure_remark) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.given_name = given_name;
		this.surname = surname;
		this.nationality = nationality;
		this.passnum = passnum;
		this.dob = dob;
		this.date_of_departure = date_of_departure;
		this.time_of_departure = time_of_departure;
		this.entered_on = entered_on;
		this.departure_remark = departure_remark;
	}
	
	public DepartDetailsPdf(String form_c_appl_id, String given_name, String surname, String nationality,
			String passnum, String dob, String date_of_departure, String time_of_departure, String entered_on,
			String departure_remark,String accom_details) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.given_name = given_name;
		this.surname = surname;
		this.nationality = nationality;
		this.passnum = passnum;
		this.dob = dob;
		this.date_of_departure = date_of_departure;
		this.time_of_departure = time_of_departure;
		this.entered_on = entered_on;
		this.departure_remark = departure_remark;
		this.accom_details=accom_details;
	}
	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}
	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPassnum() {
		return passnum;
	}
	public void setPassnum(String passnum) {
		this.passnum = passnum;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTime_of_departure() {
		return time_of_departure;
	}
	public void setTime_of_departure(String time_of_departure) {
		this.time_of_departure = time_of_departure;
	}

	public String getDeparture_remark() {
		return departure_remark;
	}
	public void setDeparture_remark(String departure_remark) {
		this.departure_remark = departure_remark;
	}
	public String getDate_of_departure() {
		return date_of_departure;
	}
	public void setDate_of_departure(String date_of_departure) {
		this.date_of_departure = date_of_departure;
	}
	public String getEntered_on() {
		return entered_on;
	}
	public void setEntered_on(String entered_on) {
		this.entered_on = entered_on;
	}
	public String getAccom_details() {
		return accom_details;
	}
	public void setAccom_details(String accom_details) {
		this.accom_details = accom_details;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	} 
	
	
	
	
	
}
