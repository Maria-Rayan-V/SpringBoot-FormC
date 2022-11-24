package ivfrt.frt.frro.web.formc;

import java.util.List;

public interface FormCRepository {

	public FormC insertFormCdata(FormC formc) throws Exception;

	public List<FormC> findByAppId(String appid);

	public List<FormC> findByAppIdForEdit(String appid);

	public List<FormC> findByUserId(String userid, String acco_code);

	public List<ApplicationList> pendingApplicationList(String userid, String frroCode);

	public List<ApplicationList> applicationList(String userid, String frroCode);

	public List<FormC> findByPassnumAndNat(String passportNo, String nationality);

	public String fetchUserIdAfterFinalSumbmit(String applicationId) throws Exception;

	public List<ApplicationList> fetchPendingApplicationList(String pass, String nat, String user) throws Exception;

	public List<ApplicationList> fetchSubmittedApplicationList(String pass, String nat, String user) throws Exception;
}
