package ivfrt.frt.frro.web.masters.model;

public class MannerOfAcquringCitizenShip {

	private String citizenship_code;
	private String citizenship_desc;
	
	public MannerOfAcquringCitizenShip() {super();}
	
	public MannerOfAcquringCitizenShip(String citizenship_code, String citizenship_desc) {
		super();
		this.citizenship_code = citizenship_code;
		this.citizenship_desc = citizenship_desc;
	}
	public String getCitizenship_code() {
		return citizenship_code;
	}
	public void setCitizenship_code(String citizenship_code) {
		this.citizenship_code = citizenship_code;
	}
	public String getCitizenship_desc() {
		return citizenship_desc;
	}
	public void setCitizenship_desc(String citizenship_desc) {
		this.citizenship_desc = citizenship_desc;
	}
	
	
	
	
}
