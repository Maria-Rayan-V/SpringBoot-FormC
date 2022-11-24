package ivfrt.frt.frro.web.formc.registration;

public class OwnerDetails {
	private String name;
	private String address;
	private String state;
	private String cityDist;
	private String emailId;
	private String phoneNum;
	private String mobile;
	private String acco_code;
	private String owner_code;
	private String frro_code;
	 
	public OwnerDetails() {
		super();
	}


	public OwnerDetails(String name, String address, String state, String cityDist, String emailId, String phoneNum,
			String mobile) {
		super();
		this.name = name;
		this.address = address;
		this.state = state;
		this.cityDist = cityDist;
		this.emailId = emailId;
		this.phoneNum = phoneNum;
		this.mobile = mobile;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	


	public String getCityDist() {
		return cityDist;
	}


	public void setCityDist(String cityDist) {
		this.cityDist = cityDist;
	}


	public String getAcco_code() {
		return acco_code;
	}


	public void setAcco_code(String acco_code) {
		this.acco_code = acco_code;
	}


	public String getOwner_code() {
		return owner_code;
	}


	public void setOwner_code(String owner_code) {
		this.owner_code = owner_code;
	}


	public String getFrro_code() {
		return frro_code;
	}


	public void setFrro_code(String frro_code) {
		this.frro_code = frro_code;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
