package ivfrt.frt.frro.web.service.services.finalsubmit;

public class ApplicantDetailByUserBasic {

	private String applicationId;
	private String applicantName;
	private String natinality;
	private String natinality_name;
	private String gender;
	private String gender_desc;
	private String dob;
	private String enteredOn;
	private String web_stage;
	
	
	
	
	public ApplicantDetailByUserBasic() {
		super();
	}
	public ApplicantDetailByUserBasic(String applicationId, String applicantName, String natinality, String gender,
			String dob, String enteredOn,String web_stage ) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.natinality = natinality;
		this.gender = gender;
		this.dob = dob;
		this.enteredOn = enteredOn;
		this.web_stage=web_stage;
	}
	public ApplicantDetailByUserBasic(String applicationId, String applicantName, String natinality,String natinality_name, String gender,String gender_desc,
			String dob, String enteredOn,String web_stage ) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.natinality = natinality;
		this.natinality_name=natinality_name;
		this.gender = gender;
		this.gender_desc = gender_desc;
		this.dob = dob;
		this.enteredOn = enteredOn;
		this.web_stage=web_stage;
	}
	public ApplicantDetailByUserBasic(String applicationId, String applicantName, String natinality, String gender,
			String dob, String enteredOn) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.natinality = natinality;
		this.gender = gender;
		this.dob = dob;
		this.enteredOn = enteredOn;
	}
	public ApplicantDetailByUserBasic(String applicationId, String applicantName, String natinality,String natinality_name,
			String gender, String gender_desc,
			String dob, String enteredOn,int a) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.natinality = natinality;
		this.natinality_name=natinality_name;
		this.gender = gender;
		this.gender_desc = gender_desc;
		this.dob = dob;
		this.enteredOn = enteredOn;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getNatinality() {
		return natinality;
	}
	public void setNatinality(String natinality) {
		this.natinality = natinality;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEnteredOn() {
		return enteredOn;
	}
	public void setEnteredOn(String enteredOn) {
		this.enteredOn = enteredOn;
	}
	public String getWeb_stage() {
		return web_stage;
	}
	public void setWeb_stage(String web_stage) {
		this.web_stage = web_stage;
	}
	public String getNatinality_name() {
		return natinality_name;
	}
	public void setNatinality_name(String natinality_name) {
		this.natinality_name = natinality_name;
	}
	
	
}
