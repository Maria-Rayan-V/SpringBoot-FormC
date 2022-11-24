package ivfrt.frt.frro.web.formc.mainform;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class FormC_PassPortVisa {

	@NotNull(message = "Form c application id required.")
	private String form_c_appl_id;
	private String passnum;
	private String passplace;
	private String passcoun;
	private String passdate;
	private String passexpdate;
	private String visanum;
	private String visaplace;
	private String visacoun;
	private String visadate;
	private String visaexpdate;

	private String visatype;
	private String visasubtype;

	public FormC_PassPortVisa() {
		super();
	}

	public FormC_PassPortVisa(String form_c_appl_id, String passnum, String passplace, String passcoun, String passdate,
			String passexpdate, String visanum, String visaplace, String visacoun, String visadate, String visaexpdate,
			String visatype, String visasubtype) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.passnum = passnum;
		this.passplace = passplace;
		this.passcoun = passcoun;
		this.passdate = passdate;
		this.passexpdate = passexpdate;
		this.visanum = visanum;
		this.visaplace = visaplace;
		this.visacoun = visacoun;
		this.visadate = visadate;
		this.visaexpdate = visaexpdate;
		this.visatype = visatype;
		this.visasubtype = visasubtype;
	}

	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public String getPassnum() {
		return passnum;
	}

	public void setPassnum(String passnum) {
		this.passnum = passnum;
	}

	public String getPassplace() {
		return passplace;
	}

	public void setPassplace(String passplace) {
		this.passplace = passplace;
	}

	public String getPasscoun() {
		return passcoun;
	}

	public void setPasscoun(String passcoun) {
		this.passcoun = passcoun;
	}

	public String getPassdate() {
		return passdate;
	}

	public void setPassdate(String passdate) {
		this.passdate = passdate;
	}

	public String getPassexpdate() {
		return passexpdate;
	}

	public void setPassexpdate(String passexpdate) {
		this.passexpdate = passexpdate;
	}

	public String getVisanum() {
		return visanum;
	}

	public void setVisanum(String visanum) {
		this.visanum = visanum;
	}

	public String getVisaplace() {
		return visaplace;
	}

	public void setVisaplace(String visaplace) {
		this.visaplace = visaplace;
	}

	public String getVisacoun() {
		return visacoun;
	}

	public void setVisacoun(String visacoun) {
		this.visacoun = visacoun;
	}

	public String getVisadate() {
		return visadate;
	}

	public void setVisadate(String visadate) {
		this.visadate = visadate;
	}

	public String getVisaexpdate() {
		return visaexpdate;
	}

	public void setVisaexpdate(String visaexpdate) {
		this.visaexpdate = visaexpdate;
	}

	public String getVisatype() {
		return visatype;
	}

	public void setVisatype(String visatype) {
		this.visatype = visatype;
	}

	public String getVisasubtype() {
		return visasubtype;
	}

	public void setVisasubtype(String visasubtype) {
		this.visasubtype = visasubtype;
	}

}
