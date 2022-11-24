package ivfrt.frt.frro.web.formc.mainform;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormC_RefContact {

	private String form_c_appl_id;
	private String addrofrefinind;
	@NotNull
	private String stateofrefinind;
	@NotNull
	private String cityofrefinind;
	@NotNull
	@Size(min = 6, message = "Pin code should have atleast 6 characters")
	private String pincodeofref;
	private String mblnuminind;
	private String phnnuminind;
	private String mblnum;
	private String phnnum;
	private String employedinind;
	//@NotNull
	//private String splcategorycode;
	private String purposeofvisit;

	public FormC_RefContact() {
		super();
	}

	public FormC_RefContact(String form_c_appl_id, String addrofrefinind, @NotNull String stateofrefinind,
			@NotNull String cityofrefinind,
			@NotNull @Size(min = 6, message = "Pin code should have atleast 6 characters") String pincodeofref,
			String mblnuminind, String phnnuminind, String mblnum, String phnnum, String employedinind,
			 String purposeofvisit) { 
		/* @NotNull String splcategorycode, */
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.addrofrefinind = addrofrefinind;
		this.stateofrefinind = stateofrefinind;
		this.cityofrefinind = cityofrefinind;
		this.pincodeofref = pincodeofref;
		this.mblnuminind = mblnuminind;
		this.phnnuminind = phnnuminind;
		this.mblnum = mblnum;
		this.phnnum = phnnum;
		this.employedinind = employedinind;
		//this.splcategorycode = splcategorycode;
		this.purposeofvisit = purposeofvisit;
	}

	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public String getAddrofrefinind() {
		return addrofrefinind;
	}

	public void setAddrofrefinind(String addrofrefinind) {
		this.addrofrefinind = addrofrefinind;
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

	public String getPincodeofref() {
		return pincodeofref;
	}

	public void setPincodeofref(String pincodeofref) {
		this.pincodeofref = pincodeofref;
	}

	public String getMblnuminind() {
		return mblnuminind;
	}

	public void setMblnuminind(String mblnuminind) {
		this.mblnuminind = mblnuminind;
	}

	public String getPhnnuminind() {
		return phnnuminind;
	}

	public void setPhnnuminind(String phnnuminind) {
		this.phnnuminind = phnnuminind;
	}

	public String getMblnum() {
		return mblnum;
	}

	public void setMblnum(String mblnum) {
		this.mblnum = mblnum;
	}

	public String getPhnnum() {
		return phnnum;
	}

	public void setPhnnum(String phnnum) {
		this.phnnum = phnnum;
	}

	public String getEmployedinind() {
		return employedinind;
	}

	public void setEmployedinind(String employedinind) {
		this.employedinind = employedinind;
	}

	/*
	 * public String getSplcategorycode() { return splcategorycode; }
	 * 
	 * public void setSplcategorycode(String splcategorycode) { this.splcategorycode
	 * = splcategorycode; }
	 */

	public String getPurposeofvisit() {
		return purposeofvisit;
	}

	public void setPurposeofvisit(String purposeofvisit) {
		this.purposeofvisit = purposeofvisit;
	}

}
