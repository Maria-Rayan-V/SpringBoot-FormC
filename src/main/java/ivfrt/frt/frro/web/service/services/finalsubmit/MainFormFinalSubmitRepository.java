package ivfrt.frt.frro.web.service.services.finalsubmit;


public interface MainFormFinalSubmitRepository {

	MainFormFinalSubmit finalSubmit(MainFormFinalSubmit mainFormFinalSubmit) throws Exception;	
	String fetchUserId(String applicationId) throws Exception;
}
