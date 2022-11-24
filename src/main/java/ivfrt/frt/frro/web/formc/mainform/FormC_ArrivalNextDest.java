package ivfrt.frt.frro.web.formc.mainform;

import javax.validation.constraints.Pattern;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class FormC_ArrivalNextDest {

	private String form_c_appl_id;

	private String arriplace;
	@Pattern(regexp = ConstantUtil.ALPHA_NUM, message = ConstantUtil.ERR_MSG + "Arrival city must not be blank.")
	private String arricit;
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Arrival country must not be blank.")
	private String arricoun;
	private String arridateind;
	private String arridatehotel;
	private String arritimehotel;
	private String durationofstay;
	private String nextdestplaceinind;
	private String nextdestdistinind;
	private String nextdeststateinind;
	private String nextdestcounflag;
	private String nextdestplaceoutind;
	private String nextdestcityoutind;
	private String nextdestcounoutind;

	public FormC_ArrivalNextDest() {
		super();
	}

	public FormC_ArrivalNextDest(String form_c_appl_id, String arriplace,
			 String arricit,
			 String arricoun,
			 String arridateind,
			 String arridatehotel,
			 String arritimehotel,
			 String durationofstay,
			String nextdestplaceinind, String nextdestdistinind, String nextdeststateinind, String nextdestcounflag,
			String nextdestplaceoutind, String nextdestcityoutind, String nextdestcounoutind) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.arriplace = arriplace;
		this.arricit = arricit;
		this.arricoun = arricoun;
		this.arridateind = arridateind;
		this.arridatehotel = arridatehotel;
		this.arritimehotel = arritimehotel;
		this.durationofstay = durationofstay;
		this.nextdestplaceinind = nextdestplaceinind;
		this.nextdestdistinind = nextdestdistinind;
		this.nextdeststateinind = nextdeststateinind;
		this.nextdestcounflag = nextdestcounflag;
		this.nextdestplaceoutind = nextdestplaceoutind;
		this.nextdestcityoutind = nextdestcityoutind;
		this.nextdestcounoutind = nextdestcounoutind;
	}

	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public String getArriplace() {
		return arriplace;
	}

	public void setArriplace(String arriplace) {
		this.arriplace = arriplace;
	}

	public String getArricit() {
		return arricit;
	}

	public void setArricit(String arricit) {
		this.arricit = arricit;
	}

	public String getArricoun() {
		return arricoun;
	}

	public void setArricoun(String arricoun) {
		this.arricoun = arricoun;
	}

	public String getArridateind() {
		return arridateind;
	}

	public void setArridateind(String arridateind) {
		this.arridateind = arridateind;
	}

	public String getArridatehotel() {
		return arridatehotel;
	}

	public void setArridatehotel(String arridatehotel) {
		this.arridatehotel = arridatehotel;
	}

	public String getArritimehotel() {
		return arritimehotel;
	}

	public void setArritimehotel(String arritimehotel) {
		this.arritimehotel = arritimehotel;
	}

	public String getDurationofstay() {
		return durationofstay;
	}

	public void setDurationofstay(String durationofstay) {
		this.durationofstay = durationofstay;
	}

	public String getNextdestplaceinind() {
		return nextdestplaceinind;
	}

	public void setNextdestplaceinind(String nextdestplaceinind) {
		this.nextdestplaceinind = nextdestplaceinind;
	}

	public String getNextdestdistinind() {
		return nextdestdistinind;
	}

	public void setNextdestdistinind(String nextdestdistinind) {
		this.nextdestdistinind = nextdestdistinind;
	}

	public String getNextdeststateinind() {
		return nextdeststateinind;
	}

	public void setNextdeststateinind(String nextdeststateinind) {
		this.nextdeststateinind = nextdeststateinind;
	}

	public String getNextdestcounflag() {
		return nextdestcounflag;
	}

	public void setNextdestcounflag(String nextdestcounflag) {
		this.nextdestcounflag = nextdestcounflag;
	}

	public String getNextdestplaceoutind() {
		return nextdestplaceoutind;
	}

	public void setNextdestplaceoutind(String nextdestplaceoutind) {
		this.nextdestplaceoutind = nextdestplaceoutind;
	}

	public String getNextdestcityoutind() {
		return nextdestcityoutind;
	}

	public void setNextdestcityoutind(String nextdestcityoutind) {
		this.nextdestcityoutind = nextdestcityoutind;
	}

	public String getNextdestcounoutind() {
		return nextdestcounoutind;
	}

	public void setNextdestcounoutind(String nextdestcounoutind) {
		this.nextdestcounoutind = nextdestcounoutind;
	}

}
