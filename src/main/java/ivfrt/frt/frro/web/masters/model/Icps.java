package ivfrt.frt.frro.web.masters.model;

public class Icps {

	private String icpsrno;
	private String icpDesc;
	private String icpTypeCode;
	
	
	
	public Icps(String icpsrno, String icpDesc, String icpTypeCode) {
		super();
		this.icpsrno = icpsrno;
		this.icpDesc = icpDesc;
		this.icpTypeCode = icpTypeCode;
	}
	public String getIcpsrno() {
		return icpsrno;
	}
	public void setIcpsrno(String icpsrno) {
		this.icpsrno = icpsrno;
	}
	public String getIcpDesc() {
		return icpDesc;
	}
	public void setIcpDesc(String icpDesc) {
		this.icpDesc = icpDesc;
	}
	public String getIcpTypeCode() {
		return icpTypeCode;
	}
	public void setIcpTypeCode(String icpTypeCode) {
		this.icpTypeCode = icpTypeCode;
	}
	
	
	
	
}
