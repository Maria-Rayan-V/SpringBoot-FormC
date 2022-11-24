package ivfrt.frt.frro.web.formc;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApplicationList {

	private String form_c_appl_id;
	private String given_name;
	private String surname;
	private String nationality;
	private String nationality_desc;
	private String country_outside_india;
	private String country_outside_india_desc;
	private String passnum;
	
	private String dob;
	@NotBlank
	@Size(min = 20000, max = 100000, message = ".jpg image is required. size between 20kb to 100kb ")
	private String img;

	@JsonIgnore
	private byte[] imgData;
	
	public ApplicationList() {
		super();
	}
	
	

	public ApplicationList(String form_c_appl_id, String given_name, String surname, String nationality,
			String nationality_desc, String country_outside_india, String country_outside_india_desc, String passnum) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.given_name = given_name;
		this.surname = surname;
		this.nationality = nationality;
		this.nationality_desc = nationality_desc;
		this.country_outside_india = country_outside_india;
		this.country_outside_india_desc = country_outside_india_desc;
		this.passnum = passnum;
	}

	public ApplicationList(String form_c_appl_id, String given_name, String surname, String nationality,
			String nationality_desc, String country_outside_india, String country_outside_india_desc, String passnum,String dob,String img) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.given_name = given_name;
		this.surname = surname;
		this.nationality = nationality;
		this.nationality_desc = nationality_desc;
		this.country_outside_india = country_outside_india;
		this.country_outside_india_desc = country_outside_india_desc;
		this.passnum = passnum;
		this.dob=dob;
		this.img=img;
		
	}

	public String getNationality_desc() {
		return nationality_desc;
	}



	public void setNationality_desc(String nationality_desc) {
		this.nationality_desc = nationality_desc;
	}



	public String getCountry_outside_india_desc() {
		return country_outside_india_desc;
	}



	public void setCountry_outside_india_desc(String country_outside_india_desc) {
		this.country_outside_india_desc = country_outside_india_desc;
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
	public String getCountry_outside_india() {
		return country_outside_india;
	}
	public void setCountry_outside_india(String country_outside_india) {
		this.country_outside_india = country_outside_india;
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public byte[] getImgData() {
		return imgData;
	}



	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}
	
	
	
	
}
