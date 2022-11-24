package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;


import ivfrt.frt.frro.web.masters.model.VisitPurpose;

public interface VisitPurposeRepository {

	List<VisitPurpose> findAll();
	List<VisitPurpose> findVisitPurposeByCode(String purposeCode);
}
