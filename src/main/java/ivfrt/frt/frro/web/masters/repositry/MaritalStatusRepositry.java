package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.MaritalStatus;

public interface MaritalStatusRepositry {

	List<MaritalStatus> findAll();
	List<MaritalStatus> findByMaritalStatusCode(String mStatusCode);
}
