package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.VisaEntries;

public interface VisaEntriesRepositry {

	List<VisaEntries> findAll();
	List<VisaEntries> findEntryTypeNameByCode(String vEntryTypeCode);
}
