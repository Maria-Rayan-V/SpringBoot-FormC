package ivfrt.frt.frro.web.formc.mainform;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class FormC_GenAppID {

	private String form_c_appl_id;
	@NotBlank(message = "Gender may not be blank")
	@Pattern(regexp = ConstantUtil.SINGLE_CHAR, message = ConstantUtil.ERR_MSG + "Gender.")
	private String gender;
	@NotNull(message = "Country out side india may not be blank.")
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Country out side India.")
	private String country_outside_india;
	private String acco_code;
	private String entered_by;
	private Date entered_on;
	private String stateofrefinind;
	private String cityofrefinind;
	private String frro_fro_code;
	@NotNull(message = "Given Name may not be blank.")
	private String given_name;
	private String surname;
	private String message;

	public FormC_GenAppID() {
		super();
	}

	public FormC_GenAppID(String form_c_appl_id, String gender, String nationality, String acco_code, String entered_by,
			Date entered_on, String stateofrefinind, String cityofrefinind) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.gender = gender;
		this.country_outside_india = nationality;
		this.acco_code = acco_code;
		this.entered_by = entered_by;
		this.entered_on = entered_on;
		this.stateofrefinind = stateofrefinind;
		this.cityofrefinind = cityofrefinind;
	}

	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry_outside_india() {
		return country_outside_india;
	}

	public void setCountry_outside_india(String country_outside_india) {
		this.country_outside_india = country_outside_india;
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

	public String getStateofrefinind() {
		return stateofrefinind;
	}

	public void setStateofrefinind(String stateofrefinind) {
		this.stateofrefinind = stateofrefinind;
	}

	public String getCityofrefinind() {
		return cityofrefinind;
	}

	public void setCityofrefinind(String cityofrefinind) {
		this.cityofrefinind = cityofrefinind;
	}

	public String getFrro_fro_code() {
		return frro_fro_code;
	}

	public void setFrro_fro_code(String frro_fro_code) {
		this.frro_fro_code = frro_fro_code;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FormC_GenAppID [form_c_appl_id=" + form_c_appl_id + ", gender=" + gender + ", nationality="
				+ country_outside_india + ", acco_code=" + acco_code + ", entered_by=" + entered_by + ", entered_on="
				+ entered_on + ", stateofrefinind=" + stateofrefinind + ", cityofrefinind=" + cityofrefinind + "]";
	}

}
