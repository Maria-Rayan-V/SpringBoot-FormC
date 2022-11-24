package ivfrt.frt.frro.web.masters.model;

public class CityDistrict {
	private String city_code;
	private String city_name;
	private String state_code;
	
	
	
	public CityDistrict(String city_code, String city_name, String state_code) {
		super();
		this.city_code = city_code;
		this.city_name = city_name;
		this.state_code = state_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	
	
	
}
