package ivfrt.frt.frro.web.masters.model;

public class MaritalStatus {

	private String mStatusCode;
	private String mStatusDesc;
	
	
	
	
	public MaritalStatus(String mStatusCode, String mStatusDesc) {
		super();
		this.mStatusCode = mStatusCode;
		this.mStatusDesc = mStatusDesc;
	}
	
	public String getmStatusCode() {
		return mStatusCode;
	}
	public void setmStatusCode(String mStatusCode) {
		this.mStatusCode = mStatusCode;
	}
	public String getmStatusDesc() {
		return mStatusDesc;
	}
	public void setmStatusDesc(String mStatusDesc) {
		this.mStatusDesc = mStatusDesc;
	}
	
	
}
