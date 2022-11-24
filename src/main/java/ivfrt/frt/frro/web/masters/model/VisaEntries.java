package ivfrt.frt.frro.web.masters.model;

public class VisaEntries {
private String entryTypeCode;
private String entryTypeName;



public VisaEntries(String entryTypeCode, String entryTypeName) {
	super();
	this.entryTypeCode = entryTypeCode;
	this.entryTypeName = entryTypeName;
}



public String getEntryTypeCode() {
	return entryTypeCode;
}



public void setEntryTypeCode(String entryTypeCode) {
	this.entryTypeCode = entryTypeCode;
}



public String getEntryTypeName() {
	return entryTypeName;
}



public void setEntryTypeName(String entryTypeName) {
	this.entryTypeName = entryTypeName;
}




}
