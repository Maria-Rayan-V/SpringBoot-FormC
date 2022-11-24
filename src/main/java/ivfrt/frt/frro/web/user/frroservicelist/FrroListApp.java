package ivfrt.frt.frro.web.user.frroservicelist;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FrroListApp {

	private String frroCode;
	private String frroName;
	@JsonIgnore
	private String natinality;
	@JsonIgnore
	private String state;
	@JsonIgnore
	private String cityDist;
	
	
	
	public FrroListApp(String frroCode, String frroName) {
		super();
		this.frroCode = frroCode;
		this.frroName = frroName;
	
	}
	
	public FrroListApp(String frroCode, String frroName, String natinality, String state, String cityDist) {
		super();
		this.frroCode = frroCode;
		this.frroName = frroName;
		this.natinality = natinality;
		this.state = state;
		this.cityDist = cityDist;
	}
	
	public FrroListApp(String frroCode, String frroName, String natinality) {
		super();
		this.frroCode = frroCode;
		this.frroName = frroName;
		this.natinality = natinality;
	}
	
	public String getFrroCode() {
		return frroCode;
	}
	public void setFrroCode(String frroCode) {
		this.frroCode = frroCode;
	}
	public String getFrroName() {
		return frroName;
	}
	public void setFrroName(String frroName) {
		this.frroName = frroName;
	}
	public String getNatinality() {
		return natinality;
	}
	public void setNatinality(String natinality) {
		this.natinality = natinality;
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

	@Override
	public String toString() {
		return "FrroListApp [frroCode=" + frroCode + ", frroName=" + frroName + ", natinality=" + natinality
				+ ", state=" + state + ", cityDist=" + cityDist + "]";
	}
	
	
	
	
	
}
