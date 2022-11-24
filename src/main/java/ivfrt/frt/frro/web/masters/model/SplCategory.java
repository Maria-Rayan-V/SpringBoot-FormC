package ivfrt.frt.frro.web.masters.model;

public class SplCategory {

	private String splCatCode;
	private String splCatDesc;
	
	
	
	public SplCategory(String splCatCode, String splCatDesc) {
		super();
		this.splCatCode = splCatCode;
		this.splCatDesc = splCatDesc;
	}
	public String getSplCatCode() {
		return splCatCode;
	}
	public void setSplCatCode(String splCatCode) {
		this.splCatCode = splCatCode;
	}
	public String getSplCatDesc() {
		return splCatDesc;
	}
	public void setSplCatDesc(String splCatDesc) {
		this.splCatDesc = splCatDesc;
	}
	
	
}
