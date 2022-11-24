package ivfrt.frt.frro.web.formc;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ivfrt.frt.frro.web.utilities.ConstantUtil;

public class FormC {
	@Pattern(regexp = ConstantUtil.APPID, message = "Invalid Application Id.")
	private String form_c_appl_id;
	@NotNull(message = "Acco_code may not be blank")
	@Pattern(regexp = ConstantUtil.ACCO_CODE, message = "Invalid Accommodation code.")
	private String acco_code;
	@Pattern(regexp = ConstantUtil.REMARK, message = "Invalid Remark.")
	private String remark;
	@NotBlank(message = "entered_by not be blank")
	private String entered_by;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Pattern(regexp = ConstantUtil.DATE_DDMMYYYY, message = "Entred on : DD/MM/YYYY format required")
	private Date entered_on;
	@Pattern(regexp = ConstantUtil.ALPHA_SP, message = ConstantUtil.ERR_MSG + "Name.")
	@NotBlank(message = "Name may not be blank")
	private String name;
	@Pattern(regexp = ConstantUtil.ALPHA_SP, message = ConstantUtil.ERR_MSG + "Sur Name.")
	private String surname;
	@NotBlank(message = "Gender may not be blank")
	@Pattern(regexp = ConstantUtil.SINGLE_CHAR, message = ConstantUtil.ERR_MSG + "Gender.")
	private String gender;
	private String genderDesc;
	@NotBlank(message = "Date format may not be blank")
	private String dobformat;
	@NotBlank(message = "Date of birth may not be blank")
	private String dob;
	@Size(min = 3, max = 3)
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Nationality.")
	@NotBlank(message = "Nationality may not be blank")
	private String nationality;
	private String nationalityDesc;
	@NotNull(message = "Address out side india may not be blank.")
	private String addroutind;
	@NotNull(message = "City out side india may not be blank.")
	private String cityoutind;
	@NotNull(message = "Country out side india may not be blank.")
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Country out side India.")
	private String counoutind;
	private String counoutindDesc;
	@Pattern(regexp = ConstantUtil.ALPHA_NUM, message = ConstantUtil.ERR_MSG + "Passport Number.")
	private String passnum;
	@Pattern(regexp = ConstantUtil.ALPHA_NUM_SP, message = ConstantUtil.ERR_MSG + "Passport Issue Place.")
	private String passplace;
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Passport Issue Country.")
	private String passcoun;
	private String passcounDesc;
	@Pattern(regexp = ConstantUtil.DATE_DDMMYYYY, message = ConstantUtil.ERR_MSG + "Passport Issue Date.")
	private String passdate;
	@Pattern(regexp = ConstantUtil.DATE_DDMMYYYY, message = ConstantUtil.ERR_MSG + "Passport Expiry Date.")
	private String passexpdate;
	@Pattern(regexp = ConstantUtil.ALPHA_NUM, message = ConstantUtil.ERR_MSG + "Visa Number.")
	private String visanum;
	@Pattern(regexp = ConstantUtil.ALPHA_NUM, message = ConstantUtil.ERR_MSG + "Visa Issue Place.")
	private String visaplace;
	@Pattern(regexp = ConstantUtil.NAT_CODE, message = ConstantUtil.ERR_MSG + "Visa Issue Country.")
	private String visacoun;
	private String visacounDesc;
	@Pattern(regexp = ConstantUtil.DATE_DDMMYYYY, message = ConstantUtil.ERR_MSG + "Visa Issue Date.")
	private String visadate;
	@Pattern(regexp = ConstantUtil.DATE_DDMMYYYY, message = ConstantUtil.ERR_MSG + "Visa Expiry Date.")
	private String visaexpdate;
	private String visatype;
	private String visatypeDesc;
	private String visasubtype;
	private String visasubtypeDesc;
	@NotBlank(message = "Arrival place may not be blank")
	private String arriplace;
	@NotBlank(message = "Arrival city may not be blank")
	private String arricit;
	@NotBlank(message = "Arrival country may not be blank")
	private String arricoun;
	private String arricounDesc;
	@NotBlank(message = "Arrival date may not be blank")
	private String arridateind;
	@NotBlank(message = "Arrival Date in hotel may not be blank")
	private String arridatehotel;
	@NotBlank(message = "Arrival time in hotel may not be blank")
	private String arritimehotel;
	@NotBlank(message = "Intended duration of Stay in Hotel/Institute may not be blank")
	private String durationofstay;
	private String nextdestplaceinind;
	private String nextdestdistinind;
	private String nextdestdistinindDesc;
	private String nextdeststateinind;
	private String nextdeststateinindDesc;
	private String nextdestcounflag;
	private String nextdestplaceoutind;
	private String nextdestcityoutind;
	private String nextdestcounoutind;
	private String nextdestcounoutindDesc;
	@NotBlank(message = "Arrival time in hotel may not be blank")
	private String addrofrefinind;
	@NotNull
	private String stateofrefinind;
	private String stateofrefinindDesc;
	@NotNull
	private String cityofrefinind;
	private String cityofrefinindDesc;
	@NotNull
	@Size(min = 6, message = "Pin code should have atleast 6 characters")
	private String pincodeofref;
	private String mblnuminind;
	private String phnnuminind;
	private String mblnum;
	private String phnnum;
	private String employedinind;
	private String employedinindDesc;
	@NotNull
	private String splcategorycode;
	private String splcategorycodeDesc;
	private String purposeofvisit;
	private String purposeofvisitDesc;

	@NotBlank
	@Size(min = 20000, max = 100000, message = ".jpg image is required. size between 20kb to 100kb ")
	private String img;

	@JsonIgnore
	private byte[] imgData;

	@NotBlank(message = "FRRO/ FRO  may not be blank")
	private String frro_fro_code;

	public FormC(String name, String surname, String gender, String dobformat, String dob, String nationality,
			String addroutind, String cityoutind, String counoutind, String passnum, String passplace, String passcoun,
			String passdate, String passexpdate, String visanum, String visaplace, String visacoun, String visadate,
			String visaexpdate, String visatype, String visasubtype, String arriplace, String arricit, String arricoun,
			String arridateind, String arridatehotel, String arritimehotel, String durationofstay,
			String nextdestplaceinind, String nextdestdistinind, String nextdeststateinind, String nextdestcounflag,
			String nextdestplaceoutind, String nextdestcityoutind, String nextdestcounoutind, String addrofrefinind,
			String stateofrefinind, String cityofrefinind, String pincodeofref, String mblnuminind, String phnnuminind,
			String mblnum, String phnnum, String employedinind, String splcategorycode, String purposeofvisit) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dobformat = dobformat;
		this.dob = dob;
		this.nationality = nationality;
		this.addroutind = addroutind;
		this.cityoutind = cityoutind;
		this.counoutind = counoutind;
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
		this.addrofrefinind = addrofrefinind;
		this.stateofrefinind = stateofrefinind;
		this.cityofrefinind = cityofrefinind;
		this.pincodeofref = pincodeofref;
		this.mblnuminind = mblnuminind;
		this.phnnuminind = phnnuminind;
		this.mblnum = mblnum;
		this.phnnum = phnnum;
		this.employedinind = employedinind;
		this.splcategorycode = splcategorycode;
		this.purposeofvisit = purposeofvisit;
	}

	public FormC(String form_c_appl_id, String acco_code, String remark, String entered_by, Date entered_on,
			String name, String surname, String gender, @Size(min = 1) String dobformat, String dob, String nationality,
			String addroutind, String cityoutind, String counoutind, String passnum, String passplace, String passcoun,
			String passdate, String passexpdate, String visanum, String visaplace, String visacoun, String visadate,
			String visaexpdate, String visatype, String visasubtype, String arriplace, String arricit, String arricoun,
			String arridateind, String arridatehotel, String arritimehotel, String durationofstay,
			String nextdestplaceinind, String nextdestdistinind, String nextdeststateinind, String nextdestcounflag,
			String nextdestplaceoutind, String nextdestcityoutind, String nextdestcounoutind, String addrofrefinind,
			String stateofrefinind, String cityofrefinind, String pincodeofref, String mblnuminind, String phnnuminind,
			String mblnum, String phnnum, String employedinind, String splcategorycode, String purposeofvisit) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.acco_code = acco_code;
		this.remark = remark;
		this.entered_by = entered_by;
		this.entered_on = entered_on;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dobformat = dobformat;
		this.dob = dob;
		this.nationality = nationality;
		this.addroutind = addroutind;
		this.cityoutind = cityoutind;
		this.counoutind = counoutind;
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
		this.addrofrefinind = addrofrefinind;
		this.stateofrefinind = stateofrefinind;
		this.cityofrefinind = cityofrefinind;
		this.pincodeofref = pincodeofref;
		this.mblnuminind = mblnuminind;
		this.phnnuminind = phnnuminind;
		this.mblnum = mblnum;
		this.phnnum = phnnum;
		this.employedinind = employedinind;
		this.splcategorycode = splcategorycode;
		this.purposeofvisit = purposeofvisit;
	}

	public FormC(String form_c_appl_id, String acco_code, String remark, String entered_by, Date entered_on,
			String name, String surname, String gender, @Size(min = 1) String dobformat, String dob, String nationality,
			String addroutind, String cityoutind, String counoutind, String passnum, String passplace, String passcoun,
			String passdate, String passexpdate, String visanum, String visaplace, String visacoun, String visadate,
			String visaexpdate, String visatype, String visasubtype, String arriplace, String arricit, String arricoun,
			String arridateind, String arridatehotel, String arritimehotel, String durationofstay,
			String nextdestplaceinind, String nextdestdistinind, String nextdeststateinind, String nextdestcounflag,
			String nextdestplaceoutind, String nextdestcityoutind, String nextdestcounoutind, String addrofrefinind,
			String stateofrefinind, String cityofrefinind, String pincodeofref, String mblnuminind, String phnnuminind,
			String mblnum, String phnnum, String employedinind, String splcategorycode, String purposeofvisit,
			String img) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.acco_code = acco_code;
		this.remark = remark;
		this.entered_by = entered_by;
		this.entered_on = entered_on;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dobformat = dobformat;
		this.dob = dob;
		this.nationality = nationality;
		this.addroutind = addroutind;
		this.cityoutind = cityoutind;
		this.counoutind = counoutind;
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
		this.addrofrefinind = addrofrefinind;
		this.stateofrefinind = stateofrefinind;
		this.cityofrefinind = cityofrefinind;
		this.pincodeofref = pincodeofref;
		this.mblnuminind = mblnuminind;
		this.phnnuminind = phnnuminind;
		this.mblnum = mblnum;
		this.phnnum = phnnum;
		this.employedinind = employedinind;
		this.splcategorycode = splcategorycode;
		this.purposeofvisit = purposeofvisit;
		this.img = img;
	}

	public FormC(String form_c_appl_id, String name, String surname, @Size(min = 3, max = 3) String nationality,
			String passnum) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.name = name;
		this.surname = surname;
		this.nationality = nationality;
		this.passnum = passnum;
	}

	public FormC(String form_c_appl_id, String acco_code, String remark, String entered_by, Date entered_on,
			String name, String surname, String gender, String genderDesc, String dobformat, String dob,
			String nationality, String nationalityDesc, String addroutind, String cityoutind, String counoutind,
			String counoutindDesc, String passnum, String passplace, String passcoun, String passcounDesc,
			String passdate, String passexpdate, String visanum, String visaplace, String visacoun, String visacounDesc,
			String visadate, String visaexpdate, String visatype, String visatypeDesc, String visasubtype,
			String visasubtypeDesc, String arriplace, String arricit, String arricoun, String arricounDesc,
			String arridateind, String arridatehotel, String arritimehotel, String durationofstay,
			String nextdestplaceinind, String nextdestdistinind, String nextdestdistinindDesc,
			String nextdeststateinind, String nextdeststateinindDesc, String nextdestcounflag,
			String nextdestplaceoutind, String nextdestcityoutind, String nextdestcounoutind,String nextdestcounoutindDesc, String addrofrefinind,
			String stateofrefinind, String stateofrefinindDesc, String cityofrefinind, String cityofrefinindDesc,
			String pincodeofref, String mblnuminind, String phnnuminind, String mblnum, String phnnum,
			String employedinind, String splcategorycode, String splcategorycodeDesc, String purposeofvisit,
			String purposeofvisitDesc, String frro_fro_code, String img) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.acco_code = acco_code;
		this.remark = remark;
		this.entered_by = entered_by;
		this.entered_on = entered_on;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.genderDesc = genderDesc;
		this.dobformat = dobformat;
		this.dob = dob;
		this.nationality = nationality;
		this.nationalityDesc = nationalityDesc;
		this.addroutind = addroutind;
		this.cityoutind = cityoutind;
		this.counoutind = counoutind;
		this.counoutindDesc = counoutindDesc;
		this.passnum = passnum;
		this.passplace = passplace;
		this.passcoun = passcoun;
		this.passcounDesc = passcounDesc;
		this.passdate = passdate;
		this.passexpdate = passexpdate;
		this.visanum = visanum;
		this.visaplace = visaplace;
		this.visacoun = visacoun;
		this.visacounDesc = visacounDesc;
		this.visadate = visadate;
		this.visaexpdate = visaexpdate;
		this.visatype = visatype;
		this.visasubtype = visasubtype;
		this.arriplace = arriplace;
		this.arricit = arricit;
		this.arricoun = arricoun;
		this.arricounDesc = arricounDesc;
		this.arridateind = arridateind;
		this.arridatehotel = arridatehotel;
		this.arritimehotel = arritimehotel;
		this.durationofstay = durationofstay;
		this.nextdestplaceinind = nextdestplaceinind;
		this.nextdestdistinind = nextdestdistinind;
		this.nextdestdistinindDesc = nextdestdistinindDesc;
		this.nextdeststateinind = nextdeststateinind;
		this.nextdeststateinindDesc = nextdeststateinindDesc;
		this.nextdestcounflag = nextdestcounflag;
		this.nextdestplaceoutind = nextdestplaceoutind;
		this.nextdestcityoutind = nextdestcityoutind;
		this.nextdestcounoutind = nextdestcounoutind;
		this.nextdestcounoutindDesc = nextdestcounoutindDesc;
		this.addrofrefinind = addrofrefinind;
		this.stateofrefinind = stateofrefinind;
		this.stateofrefinindDesc = stateofrefinindDesc;
		this.cityofrefinind = cityofrefinind;
		this.cityofrefinindDesc = cityofrefinindDesc;
		this.pincodeofref = pincodeofref;
		this.mblnuminind = mblnuminind;
		this.phnnuminind = phnnuminind;
		this.mblnum = mblnum;
		this.phnnum = phnnum;
		this.employedinind = employedinind;
		this.splcategorycode = splcategorycode;
		this.splcategorycodeDesc = splcategorycodeDesc;
		this.purposeofvisit = purposeofvisit;
		this.purposeofvisitDesc = purposeofvisitDesc;
		this.frro_fro_code = frro_fro_code;
		this.img = img;
	}

	public FormC(String form_c_appl_id, String acco_code, String remark, String entered_by, Date entered_on,
			String name, String surname, String gender, String genderDesc, String dobformat, String dob,
			String nationality, String nationalityDesc, String addroutind, String cityoutind, String counoutind,
			String counoutindDesc, String passnum, String passplace, String passcoun, String passdate,
			String passexpdate, String visanum, String visaplace, String visacoun, String visadate, String visaexpdate,
			String visatype, String visasubtype, String arriplace, String arricit, String arricoun, String arridateind,
			String arridatehotel, String arritimehotel, String durationofstay, String nextdestplaceinind,
			String nextdestdistinind, String nextdeststateinind, String nextdestcounflag, String nextdestplaceoutind,
			String nextdestcityoutind, String nextdestcounoutind, String addrofrefinind, String stateofrefinind,
			String cityofrefinind, String pincodeofref, String mblnuminind, String phnnuminind, String mblnum,
			String phnnum, String employedinind, String splcategorycode, String purposeofvisit, String frro_fro_code,
			String img) {
		super();
		this.form_c_appl_id = form_c_appl_id;
		this.acco_code = acco_code;
		this.remark = remark;
		this.entered_by = entered_by;
		this.entered_on = entered_on;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.genderDesc = genderDesc;
		this.dobformat = dobformat;
		this.dob = dob;
		this.nationality = nationality;
		this.nationalityDesc = nationalityDesc;
		this.addroutind = addroutind;
		this.cityoutind = cityoutind;
		this.counoutind = counoutind;
		this.counoutindDesc = counoutindDesc;
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
		this.addrofrefinind = addrofrefinind;
		this.stateofrefinind = stateofrefinind;
		this.cityofrefinind = cityofrefinind;
		this.pincodeofref = pincodeofref;
		this.mblnuminind = mblnuminind;
		this.phnnuminind = phnnuminind;
		this.mblnum = mblnum;
		this.phnnum = phnnum;
		this.employedinind = employedinind;
		this.splcategorycode = splcategorycode;
		this.purposeofvisit = purposeofvisit;
		this.frro_fro_code = frro_fro_code;
		this.img = img;
	}

	public FormC() {
		super();
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDobformat() {
		return dobformat;
	}

	public void setDobformat(String dobformat) {
		this.dobformat = dobformat;
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

	public String getSplcategorycode() {
		return splcategorycode;
	}

	public void setSplcategorycode(String splcategorycode) {
		this.splcategorycode = splcategorycode;
	}

	public String getPurposeofvisit() {
		return purposeofvisit;
	}

	public void setPurposeofvisit(String purposeofvisit) {
		this.purposeofvisit = purposeofvisit;
	}

	public String getForm_c_appl_id() {
		return form_c_appl_id;
	}

	public void setForm_c_appl_id(String form_c_appl_id) {
		this.form_c_appl_id = form_c_appl_id;
	}

	public String getAcco_code() {
		return acco_code;
	}

	public void setAcco_code(String acco_code) {
		this.acco_code = acco_code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public byte[] getImgData() {
		return imgData;
	}

	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getFrro_fro_code() {
		return frro_fro_code;
	}

	public void setFrro_fro_code(String frro_fro_code) {
		this.frro_fro_code = frro_fro_code;
	}

	public String getGenderDesc() {
		return genderDesc;
	}

	public void setGenderDesc(String genderDesc) {
		this.genderDesc = genderDesc;
	}

	public String getNationalityDesc() {
		return nationalityDesc;
	}

	public void setNationalityDesc(String nationalityDesc) {
		this.nationalityDesc = nationalityDesc;
	}

	public String getCounoutindDesc() {
		return counoutindDesc;
	}

	public void setCounoutindDesc(String counoutindDesc) {
		this.counoutindDesc = counoutindDesc;
	}

	public String getStateofrefinindDesc() {
		return stateofrefinindDesc;
	}

	public void setStateofrefinindDesc(String stateofrefinindDesc) {
		this.stateofrefinindDesc = stateofrefinindDesc;
	}

	public String getCityofrefinindDesc() {
		return cityofrefinindDesc;
	}

	public void setCityofrefinindDesc(String cityofrefinindDesc) {
		this.cityofrefinindDesc = cityofrefinindDesc;
	}

	public String getEmployedinindDesc() {
		return employedinindDesc;
	}

	public void setEmployedinindDesc(String employedinindDesc) {
		this.employedinindDesc = employedinindDesc;
	}

	public String getPasscounDesc() {
		return passcounDesc;
	}

	public void setPasscounDesc(String passcounDesc) {
		this.passcounDesc = passcounDesc;
	}

	public String getVisacounDesc() {
		return visacounDesc;
	}

	public void setVisacounDesc(String visacounDesc) {
		this.visacounDesc = visacounDesc;
	}

	public String getVisatypeDesc() {
		return visatypeDesc;
	}

	public void setVisatypeDesc(String visatypeDesc) {
		this.visatypeDesc = visatypeDesc;
	}

	public String getVisasubtypeDesc() {
		return visasubtypeDesc;
	}

	public void setVisasubtypeDesc(String visasubtypeDesc) {
		this.visasubtypeDesc = visasubtypeDesc;
	}

	public String getArricounDesc() {
		return arricounDesc;
	}

	public void setArricounDesc(String arricounDesc) {
		this.arricounDesc = arricounDesc;
	}

	public String getNextdestdistinindDesc() {
		return nextdestdistinindDesc;
	}

	public void setNextdestdistinindDesc(String nextdestdistinindDesc) {
		this.nextdestdistinindDesc = nextdestdistinindDesc;
	}

	public String getNextdeststateinindDesc() {
		return nextdeststateinindDesc;
	}

	public void setNextdeststateinindDesc(String nextdeststateinindDesc) {
		this.nextdeststateinindDesc = nextdeststateinindDesc;
	}

	public String getNextdestcounoutindDesc() {
		return nextdestcounoutindDesc;
	}

	public void setNextdestcounoutindDesc(String nextdestcounoutindDesc) {
		this.nextdestcounoutindDesc = nextdestcounoutindDesc;
	}

	public String getSplcategorycodeDesc() {
		return splcategorycodeDesc;
	}

	public void setSplcategorycodeDesc(String splcategorycodeDesc) {
		this.splcategorycodeDesc = splcategorycodeDesc;
	}

	public String getPurposeofvisitDesc() {
		return purposeofvisitDesc;
	}

	public void setPurposeofvisitDesc(String purposeofvisitDesc) {
		this.purposeofvisitDesc = purposeofvisitDesc;
	}

	@Override
	public String toString() {
		return "FormC [form_c_appl_id=" + form_c_appl_id + ", acco_code=" + acco_code + ", remark=" + remark
				+ ", entered_by=" + entered_by + ", entered_on=" + entered_on + ", name=" + name + ", surname="
				+ surname + ", gender=" + gender + ", dobformat=" + dobformat + ", dob=" + dob + ", nationality="
				+ nationality + ", addroutind=" + addroutind + ", cityoutind=" + cityoutind + ", counoutind="
				+ counoutind + ", passnum=" + passnum + ", passplace=" + passplace + ", passcoun=" + passcoun
				+ ", passdate=" + passdate + ", passexpdate=" + passexpdate + ", visanum=" + visanum + ", visaplace="
				+ visaplace + ", visacoun=" + visacoun + ", visadate=" + visadate + ", visaexpdate=" + visaexpdate
				+ ", visatype=" + visatype + ", visasubtype=" + visasubtype + ", arriplace=" + arriplace + ", arricit="
				+ arricit + ", arricoun=" + arricoun + ", arridateind=" + arridateind + ", arridatehotel="
				+ arridatehotel + ", arritimehotel=" + arritimehotel + ", durationofstay=" + durationofstay
				+ ", nextdestplaceinind=" + nextdestplaceinind + ", nextdestdistinind=" + nextdestdistinind
				+ ", nextdeststateinind=" + nextdeststateinind + ", nextdestcounflag=" + nextdestcounflag
				+ ", nextdestplaceoutind=" + nextdestplaceoutind + ", nextdestcityoutind=" + nextdestcityoutind
				+ ", nextdestcounoutind=" + nextdestcounoutind + ", addrofrefinind=" + addrofrefinind
				+ ", stateofrefinind=" + stateofrefinind + ", cityofrefinind=" + cityofrefinind + ", pincodeofref="
				+ pincodeofref + ", mblnuminind=" + mblnuminind + ", phnnuminind=" + phnnuminind + ", mblnum=" + mblnum
				+ ", phnnum=" + phnnum + ", employedinind=" + employedinind + ", splcategorycode=" + splcategorycode
				+ ", purposeofvisit=" + purposeofvisit + ", img=" + img + ", imgData=" + Arrays.toString(imgData) + "]";
	}

}
