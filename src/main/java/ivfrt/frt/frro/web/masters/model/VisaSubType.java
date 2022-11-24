package ivfrt.frt.frro.web.masters.model;

public class VisaSubType {

	private String visaType;
	private String visaSubType;
	private String purpose;
	private String purposeCode;
	
	
	
	public VisaSubType(String visaType, String visaSubType, String purpose, String purposeCode) {
		super();
		this.visaType = visaType;
		this.visaSubType = visaSubType;
		this.purpose = purpose;
		this.purposeCode = purposeCode;
	}
	
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getVisaSubType() {
		return visaSubType;
	}
	public void setVisaSubType(String visaSubType) {
		this.visaSubType = visaSubType;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPurposeCode() {
		return purposeCode;
	}
	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	
	
	
	
	
}
