package ivfrt.frt.frro.web.formc.report;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class FormC_MisReport {

	@NotNull(message="Accommodation code is required.")
	@Pattern(regexp = ConstantUtil.ACCO_CODE,message = "Invalid accommodation code.")
	private String acco_code;
	@NotNull(message="FRRO/FRO code is required.")
	private String frro_fro_code;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Pattern(regexp = ConstantUtil.DATE_DDMMYYYY, message = "From Date : DD/MM/YYYY format required")
	private String from_date;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Pattern(regexp = ConstantUtil.DATE_DDMMYYYY, message = "To Date : DD/MM/YYYY format required")
	private String to_date;
	
	private String acco_name;
	private String acco_addr;
	private String acco_email;
	private String acco_contactno;
	private String form_c_appl_id;
	private String given_name;
	private String surname;
	private String gender;
	private String passport_number;
	private String nationality;
	private String date_of_arrival_in_hotel;
	private String time_of_arrival_in_hotel;
	private String date_of_departure;
	private String time_of_departure;
	private String departure_remark;
	private String entered_on;
	
	
	
	
	
	public FormC_MisReport() {
		super();
	}

	public FormC_MisReport(String form_c_appl_id, String given_name, String surname, String passport_number,
			String nationality, String date_of_arrival_in_hotel, String time_of_arrival_in_hotel, String entered_on,String date_of_departure,String time_of_departure,String departure_remark) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.given_name = given_name;
		this.surname = surname;
		this.passport_number = passport_number;
		this.nationality = nationality;
		this.date_of_arrival_in_hotel = date_of_arrival_in_hotel;
		this.time_of_arrival_in_hotel = time_of_arrival_in_hotel;
		this.entered_on = entered_on;
		this.date_of_departure=date_of_departure;
		this.time_of_departure=time_of_departure;
		this.departure_remark=departure_remark;
	}
	
	public FormC_MisReport(String form_c_appl_id, String given_name, String surname, String passport_number,
			String nationality, String date_of_arrival_in_hotel, String time_of_arrival_in_hotel, String entered_on) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.given_name = given_name;
		this.surname = surname;
		this.passport_number = passport_number;
		this.nationality = nationality;
		this.date_of_arrival_in_hotel = date_of_arrival_in_hotel;
		this.time_of_arrival_in_hotel = time_of_arrival_in_hotel;
		this.entered_on = entered_on;
	}
	public String getPassport_number() {
		return passport_number;
	}
	public void setPassport_number(String passport_number) {
		this.passport_number = passport_number;
	}
	public String getAcco_code() {
		return acco_code;
	}
	public void setAcco_code(String acco_code) {
		this.acco_code = acco_code;
	}
	public String getFrro_fro_code() {
		return frro_fro_code;
	}
	public void setFrro_fro_code(String frro_fro_code) {
		this.frro_fro_code = frro_fro_code;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getTime_of_arrival_in_hotel() {
		return time_of_arrival_in_hotel;
	}
	public void setTime_of_arrival_in_hotel(String time_of_arrival_in_hotel) {
		this.time_of_arrival_in_hotel = time_of_arrival_in_hotel;
	}
	
	public String getTime_of_departure() {
		return time_of_departure;
	}
	public void setTime_of_departure(String time_of_departure) {
		this.time_of_departure = time_of_departure;
	}
	
	public String getAcco_name() {
		return acco_name;
	}
	public void setAcco_name(String acco_name) {
		this.acco_name = acco_name;
	}
	public String getAcco_addr() {
		return acco_addr;
	}
	public void setAcco_addr(String acco_addr) {
		this.acco_addr = acco_addr;
	}
	public String getAcco_email() {
		return acco_email;
	}
	public void setAcco_email(String acco_email) {
		this.acco_email = acco_email;
	}
	public String getAcco_contactno() {
		return acco_contactno;
	}
	public void setAcco_contactno(String acco_contactno) {
		this.acco_contactno = acco_contactno;
	}

	public String getDeparture_remark() {
		return departure_remark;
	}

	public void setDeparture_remark(String departure_remark) {
		this.departure_remark = departure_remark;
	}

	public String getDate_of_arrival_in_hotel() {
		return date_of_arrival_in_hotel;
	}

	public void setDate_of_arrival_in_hotel(String date_of_arrival_in_hotel) {
		this.date_of_arrival_in_hotel = date_of_arrival_in_hotel;
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
	
	
	
	
	
}
