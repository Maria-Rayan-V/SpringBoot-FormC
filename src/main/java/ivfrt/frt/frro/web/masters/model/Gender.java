package ivfrt.frt.frro.web.masters.model;

public class Gender {
	private String gender_code;
	private String gender_desc;
	
	
	
	
	public Gender(String country_code, String country_name) {
		super();
		this.gender_code = country_code;
		this.gender_desc = country_name;
	}




	public String getGender_code() {
		return gender_code;
	}




	public void setGender_code(String gender_code) {
		this.gender_code = gender_code;
	}




	public String getGender_desc() {
		return gender_desc;
	}




	public void setGender_desc(String gender_desc) {
		this.gender_desc = gender_desc;
	}
	

	
}