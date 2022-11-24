package ivfrt.frt.frro.web.masters.model;

public class Service {

	private String serviceCode;
	private String serviceDesc;
	
	
	
	public Service(String serviceCode, String serviceDesc) {
		super();
		this.serviceCode = serviceCode;
		this.serviceDesc = serviceDesc;
	}
	
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	
	
	
}
