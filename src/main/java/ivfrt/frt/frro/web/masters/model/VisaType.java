package ivfrt.frt.frro.web.masters.model;

public class VisaType {

	private String visaTypeCode;
	private String visaTypeDesc;
	
	
	
	public VisaType(String visaTypeCode, String visaTypeDesc) {
		super();
		this.visaTypeCode = visaTypeCode;
		this.visaTypeDesc = visaTypeDesc;
	}
	public String getVisaTypeCode() {
		return visaTypeCode;
	}
	public void setVisaTypeCode(String visaTypeCode) {
		this.visaTypeCode = visaTypeCode;
	}
	public String getVisaTypeDesc() {
		return visaTypeDesc;
	}
	public void setVisaTypeDesc(String visaTypeDesc) {
		this.visaTypeDesc = visaTypeDesc;
	}
	
	
}
