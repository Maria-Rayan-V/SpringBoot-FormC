package ivfrt.frt.frro.web.masters.model;

public class District {

	private String districtCode;
	private String districtName;
	private String stateCode;
	
	
	
	public District(String stateCode, String districtName) {
		super();
		this.districtCode = stateCode;
		this.districtName = districtName;
	}
	
	public District(String districtCode,String districtName, String ststeName) {
		super();
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.stateCode=ststeName;
	}

	public String getDistrictCode() {
		return districtCode;
	}


	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public String getStateCode() {
		return stateCode;
	}


	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	
	
}
