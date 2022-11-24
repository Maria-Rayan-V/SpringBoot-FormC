package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Country;
import ivfrt.frt.frro.web.masters.model.VisaType;

public interface VisaTypeRepository {

	List<VisaType> findAll();
	List<VisaType> findVisaTypeByCode(String visaTypeCode);
}
