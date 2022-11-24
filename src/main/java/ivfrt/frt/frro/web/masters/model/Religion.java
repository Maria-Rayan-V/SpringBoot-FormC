package ivfrt.frt.frro.web.masters.model;

public class Religion {
	private String religion_code;
	
	private String religion_desc;
	
	public Religion() {
		super();
	}
	
	
	
	public Religion(String religion_code, String religion_desc) {
		super();
		this.religion_code = religion_code;
		this.religion_desc = religion_desc;
	}
	@Override
	public String toString() {
		return "Religion [religion_code=" + religion_code + ", religion_desc=" + religion_desc + "]";
	}	
	
	public String getReligion_code() {
		return religion_code;
	}
	public void setReligion_code(String religion_code) {
		this.religion_code = religion_code;
	}
	public String getReligion_desc() {
		return religion_desc;
	}
	public void setReligion_desc(String religion_desc) {
		this.religion_desc = religion_desc;
	}
}
