package ivfrt.frt.frro.web.formc.mainform.webapp;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class FormCWeb_GenAppID {

	private String form_c_appl_id;
	@NotNull(message = "Given Name may not be blank.")
	@Pattern(regexp = ConstantUtil.ALPHA_SP,message = "Invalid Given Name.")
	private String given_name;
	private String surname;
	@NotBlank(message = "Gender may not be blank")
	@Pattern(regexp = ConstantUtil.SINGLE_CHAR, message = ConstantUtil.ERR_MSG + "Gender.")
	private String gender;
	@NotNull(message = "Natinality may not be blank.")
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Nationality.")
	private String nationality;
	@NotNull(message="Accommodation code is required.")
	@Pattern(regexp = ConstantUtil.ACCO_CODE,message = "Invalid accommodation code.")
	private String acco_code;
	private String entered_by;
	private Date entered_on;
	@NotNull(message="FRRO/FRO code is required.")
	private String frro_fro_code;
	private String message;
	@Pattern(regexp = ConstantUtil.ALPHA_NUM, message = ConstantUtil.ERR_MSG + "Passport Number.")
	private String passnum;
	
	
	
	
	public FormCWeb_GenAppID(String form_c_appl_id,
			@NotNull(message = "Given Name may not be blank.") @Pattern(regexp = "(^[a-zA-Z ]+$)", message = "Invalid Given Name.") String given_name,
			String surname,
			@NotBlank(message = "Gender may not be blank") @Pattern(regexp = "(^[A-Z]{1})", message = "You have entred wrong entry for Input :Gender.") String gender,
			@NotNull(message = "Natinality may not be blank.") @Pattern(regexp = "(^[A-Z]{3})", message = "You have entred wrong entry for Input :Nationality.") String natinality,
			@NotNull(message = "Accommodation code is required.") @Pattern(regexp = "(^[0-9A-Z]{4})", message = "Invalid accommodation code.") String acco_code,
			String entered_by, Date entered_on, @NotNull(message = "FRRO/FRO code is required.") String frro_fro_code) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.given_name = given_name;
		this.surname = surname;
		this.gender = gender;
		this.nationality = natinality;
		this.acco_code = acco_code;
		this.entered_by = entered_by;
		this.entered_on = entered_on;
		this.frro_fro_code = frro_fro_code;
	}
	public FormCWeb_GenAppID() {
		// TODO Auto-generated constructor stub
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
	public String getAcco_code() {
		return acco_code;
	}
	public void setAcco_code(String acco_code) {
		this.acco_code = acco_code;
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
	public String getFrro_fro_code() {
		return frro_fro_code;
	}
	public void setFrro_fro_code(String frro_fro_code) {
		this.frro_fro_code = frro_fro_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPassnum() {
		return passnum;
	}
	public void setPassnum(String passnum) {
		this.passnum = passnum;
	}
	
	
}
