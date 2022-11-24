package ivfrt.frt.frro.web.formc.arrivaldeparture;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ArrivalDetail {
	private String name;
	@Pattern(regexp = "^[A-Z]{3}", message = "Nationality: Invalid input")
	@NotNull(message = "Nationality is required.")
	private String nationality;
	@NotNull(message = "Passport number is required.")
	private String passport_no;
	private String form_c_appl_id;

	private String visa_no;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Asia/Kolkata")
	private Date date_of_arrival_in_hotel;
	@NotNull(message = "FRRO/ FRO  may not be blank")
	private String frro_fro_code;
	private String nationality_name;
	private String img;

	private String time_of_arrival_in_hotel;

	@NotNull(message = "Acco_code may not be blank")
	private String acco_code;

	public ArrivalDetail(String name, String form_c_appl_id, String frro_fro_code, String nationality,
			String passport_no, String visa_no, Date date_of_arrival_in_hotel, String time_of_arrival_in_hotel,
			String acco_code, String nationality_name) {
		super();
		this.name = name;
		this.form_c_appl_id = form_c_appl_id;
		this.frro_fro_code = frro_fro_code;
		this.nationality = nationality;
		this.passport_no = passport_no;
		this.visa_no = visa_no;
		this.date_of_arrival_in_hotel = date_of_arrival_in_hotel;
		this.time_of_arrival_in_hotel = time_of_arrival_in_hotel;
		this.acco_code = acco_code;
		this.nationality_name = nationality_name;
	}

	public ArrivalDetail(String name, String form_c_appl_id, String frro_fro_code, String nationality,
			String passport_no, String visa_no, Date date_of_arrival_in_hotel, String time_of_arrival_in_hotel,
			String acco_code, String nationality_name, String img) {
		super();
		this.name = name;
		this.form_c_appl_id = form_c_appl_id;
		this.frro_fro_code = frro_fro_code;
		this.nationality = nationality;
		this.passport_no = passport_no;
		this.visa_no = visa_no;
		this.date_of_arrival_in_hotel = date_of_arrival_in_hotel;
		this.time_of_arrival_in_hotel = time_of_arrival_in_hotel;
		this.acco_code = acco_code;
		this.nationality_name = nationality_name;
		this.img = img;
	}

	public ArrivalDetail() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public String getFrro_fro_code() {
		return frro_fro_code;
	}

	public void setFrro_fro_code(String frro_fro_code) {
		this.frro_fro_code = frro_fro_code;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassport_no() {
		return passport_no;
	}

	public void setPassport_no(String passport_no) {
		this.passport_no = passport_no;
	}

	public String getVisa_no() {
		return visa_no;
	}

	public void setVisa_no(String visa_no) {
		this.visa_no = visa_no;
	}

	public Date getDate_of_arrival_in_hotel() {
		return date_of_arrival_in_hotel;
	}

	public void setDate_of_arrival_in_hotel(Date date_of_arrival_in_hotel) {
		this.date_of_arrival_in_hotel = date_of_arrival_in_hotel;
	}

	public String getTime_of_arrival_in_hotel() {
		return time_of_arrival_in_hotel;
	}

	public void setTime_of_arrival_in_hotel(String time_of_arrival_in_hotel) {
		this.time_of_arrival_in_hotel = time_of_arrival_in_hotel;
	}

	public String getAcco_code() {
		return acco_code;
	}

	public void setAcco_code(String acco_code) {
		this.acco_code = acco_code;
	}

	public String getNationality_name() {
		return nationality_name;
	}

	public void setNationality_name(String nationality_name) {
		this.nationality_name = nationality_name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
