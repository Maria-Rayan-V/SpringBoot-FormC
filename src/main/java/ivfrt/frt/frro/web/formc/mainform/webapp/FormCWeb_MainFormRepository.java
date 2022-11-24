package ivfrt.frt.frro.web.formc.mainform.webapp;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public interface FormCWeb_MainFormRepository {

	FormCWeb_GenAppID genrateAppiD(@Valid FormCWeb_GenAppID genAppIdModel) throws Exception;

	String checkForValidToken(String token, String user);
	
	public String fetchUserId(String applicationId) throws Exception;

	int deletePendingApplication( @NotBlank @Pattern(regexp = "(^[0-9a-zA-Z]{12})", message = "Invalid Application Id.") String applicationid) throws Exception;


	
	
}
