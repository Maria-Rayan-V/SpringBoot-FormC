package ivfrt.frt.frro.web.masters.model;

public class LtvCategory {

	private String ltvCatCode;
	private String ltvCatDesc;
	private String ltvNationality;
	
	
	
	public LtvCategory(String icpsrno, String ltvCatDesc, String ltvNationality) {
		super();
		this.ltvCatCode = icpsrno;
		this.ltvCatDesc = ltvCatDesc;
		this.ltvNationality = ltvNationality;
	}



	public String getLtvCatCode() {
		return ltvCatCode;
	}



	public void setLtvCatCode(String ltvCatCode) {
		this.ltvCatCode = ltvCatCode;
	}



	public String getLtvCatDesc() {
		return ltvCatDesc;
	}



	public void setLtvCatDesc(String ltvCatDesc) {
		this.ltvCatDesc = ltvCatDesc;
	}



	public String getLtvNationality() {
		return ltvNationality;
	}



	public void setLtvNationality(String ltvNationality) {
		this.ltvNationality = ltvNationality;
	}
	
	
	
	
}
