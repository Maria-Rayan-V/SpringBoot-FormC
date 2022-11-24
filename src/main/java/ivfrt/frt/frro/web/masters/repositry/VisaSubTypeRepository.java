package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.VisaSubType;

public interface VisaSubTypeRepository {

	List<VisaSubType> findAll();
	
	List<VisaSubType> findVisaSubTypeByVisaType(String visaType);
	
	List<VisaSubType> fetchPurposeByPurposeCode(String purposeCode);
	
}
