package ivfrt.frt.frro.web.user.frroservicelist;

import java.util.HashMap;
import java.util.Map;

public class FrroList {

	private String frroCode;
	private String frroName;
	private String natinality;
	
	private String state;
	private String cityDist;
	private Map<String,String> serviceList=new HashMap<String,String>();
	
	public FrroList(String frroCode, String frroName) {
		super();
		this.frroCode = frroCode;
		this.frroName = frroName;
	}

	public FrroList() {
		
	}
	
	
	


	public FrroList(Map<String, String> serviceList) {
		super();
		this.serviceList = serviceList;
	}



	public FrroList(String frroCode, String frroName, String natinality) {
		super();
		this.frroCode = frroCode;
		this.frroName = frroName;
		this.natinality = natinality;
	}

	


	public FrroList(String frroCode, String frroName, String natinality, Map<String, String> serviceList) {
		super();
		this.frroCode = frroCode;
		this.frroName = frroName;
		this.natinality = natinality;
		this.serviceList = serviceList;
	}

	public String getNatinality() {
		return natinality;
	}



	public void setNatinality(String natinality) {
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



	public Map<String, String> getServiceList() {
		return serviceList;
	}



	public void setServiceList(Map<String, String> serviceList) {
		this.serviceList = serviceList;
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
	
	
	
	
}
