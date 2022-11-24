package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.IcpType;

public interface IcpTypeRepository {

	List<IcpType> findAll();
	List<IcpType> findGenderByCode(String genderCode);
}
