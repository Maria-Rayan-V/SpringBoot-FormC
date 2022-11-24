package ivfrt.frt.frro.web.formc.tracking;

public class FormC_TrackingDetails {

	private String form_c_appl_id;
	private String given_name;
	private String surname;
	private String nationality;
	private String passnum;
	private String dob;
	private String acco_code;
	private String frro_fro_code;
	
	
	public FormC_TrackingDetails(String form_c_appl_id, String given_name, String surname, String nationality,
			String passnum, String dob) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.given_name = given_name;
		this.surname = surname;
		this.nationality = nationality;
		this.passnum = passnum;
		this.dob = dob;
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
	
	
	
}
