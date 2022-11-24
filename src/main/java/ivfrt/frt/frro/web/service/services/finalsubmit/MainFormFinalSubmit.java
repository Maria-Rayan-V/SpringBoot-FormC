package ivfrt.frt.frro.web.service.services.finalsubmit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MainFormFinalSubmit {

	@Pattern(regexp = "^[0-9a-zA-Z]+$", message ="Invalid Application id." )
	@Size(max=12, min=12)
	@NotNull
	private String application_id;
	private String serviceids [] ;
	private String status;
	
	
	public MainFormFinalSubmit() {
		super();
	}
	public MainFormFinalSubmit(String application_id, String[] serviceids) {
		super();
		this.application_id = application_id;
		this.serviceids = serviceids;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	public String[] getServiceids() {
		return serviceids;
	}
	public void setServiceids(String[] serviceids) {
		this.serviceids = serviceids;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
