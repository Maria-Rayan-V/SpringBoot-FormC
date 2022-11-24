package ivfrt.frt.frro.web.formc.mainform;

import java.util.Date;

public interface FormC_MainFormRepository {

	public FormC_GenAppID genrateAppiD(FormC_GenAppID detailsForGenAppid) throws Exception;

	public FormC_PersonalDetail submit_FormC_PersonalDetail(FormC_PersonalDetail persionalDetails) throws Exception;

	public FormC_PassPortVisa submit_FormC_PassPortVisa(FormC_PassPortVisa passvisa) throws Exception;

	public FormC_ArrivalNextDest submit_FormC_ArrivalNextDest(FormC_ArrivalNextDest arrivalDetails) throws Exception;

	public FormC_RefContact submit_FormC_RefContact(FormC_RefContact refDetails) throws Exception;

	public String fetchUserId(String applicationId) throws Exception;

	public String finalSubmitFormC(String appid) throws Exception;
	
	public String logoutUser(String token,String user,Date exptime) throws Exception;
	
	public String checkForValidToken(String token,String userid) throws Exception;
	
	
}
