package ivfrt.frt.frro.web.formc.mainform;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class FormC_PersonalDetail {

	private String form_c_appl_id;
	@Pattern(regexp = ConstantUtil.ALPHA_SP, message = ConstantUtil.ERR_MSG + "Name.")
	@NotBlank(message = "Name may not be blank")
	private String name;
	@Pattern(regexp = ConstantUtil.ALPHA_SP, message = ConstantUtil.ERR_MSG + "Sur Name.")
	private String surname;
	@NotBlank(message = "Date format may not be blank")
	private String dobformat;
	private String dob;
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Nationality.")
	@NotBlank(message = "Nationality may not be blank")
	private String nationality;
	@NotNull(message = "Address out side india may not be blank.")
	private String addroutind;
	@NotNull(message = "City out side india may not be blank.")
	private String cityoutind;
	@NotNull(message = "Country out side india may not be blank.")
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Country out side India.")
	private String counoutind;
    @NotNull(message = "Special category code required.")
    @Size(max = 2, message = "Special category code must not be greater than 2 charecter.")
	private String splcategorycode;
	
	
	
	
	
	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddroutind() {
		return addroutind;
	}

	public void setAddroutind(String addroutind) {
		this.addroutind = addroutind;
	}

	public String getCityoutind() {
		return cityoutind;
	}

	public void setCityoutind(String cityoutind) {
		this.cityoutind = cityoutind;
	}

	public String getCounoutind() {
		return counoutind;
	}

	public void setCounoutind(String counoutind) {
		this.counoutind = counoutind;
	}

	public String getDobformat() {
		return dobformat;
	}

	public void setDobformat(String dobformat) {
		this.dobformat = dobformat;
	}

	public String getSplcategorycode() {
		return splcategorycode;
	}

	public void setSplcategorycode(String splcategorycode) {
		this.splcategorycode = splcategorycode;
	}

	
}
