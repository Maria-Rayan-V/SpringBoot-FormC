package ivfrt.frt.frro.web.formc.report;

import java.util.List;

import javax.validation.Valid;

public interface FormC_MisReportRepository {

	String checkForValidToken(String token, String user);
	public String fetchUserId(String applicationId) throws Exception;
	List<FormC_MisReport> exportPdfOfArrivalDetailsList(FormC_MisReport reportParameter);
	public String fetchAccomDetails(String accom_code,String frro_fro_code);
	List<FormC_MisReport> exportPdfOfDepartureDetailsList(FormC_MisReport reqParameter);
	List<FormC_MisReport> exportPdfOfArrivalDetailsListByEntredOn(@Valid FormC_MisReport reqParameter);
	
}
