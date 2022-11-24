package ivfrt.frt.frro.web.masters.model;

public class Occupation {

	private String occupation_code;
	private String occupation_desc;
	
	
	
	
	
	public Occupation() {
		super();
	}
	
	
	
	public Occupation(String occupation_code, String occupation_desc) {
		super();
		this.occupation_code = occupation_code;
		this.occupation_desc = occupation_desc;
	}



	public String getOccupation_code() {
		return occupation_code;
	}
	public void setOccupation_code(String occupation_code) {
		this.occupation_code = occupation_code;
	}
	public String getOccupation_desc() {
		return occupation_desc;
	}
	public void setOccupation_desc(String occupation_desc) {
		this.occupation_desc = occupation_desc;
	}



	@Override
	public String toString() {
		return "Occupation [occupation_code=" + occupation_code + ", occupation_desc=" + occupation_desc + "]";
	}	
	
	
	
}
