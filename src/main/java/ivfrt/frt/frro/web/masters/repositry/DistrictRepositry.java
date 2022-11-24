package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.District;

public interface DistrictRepositry {

	List<District> findAll();
	List<District> findByDistrictCode(String distCode);
	List<District> findDistrictListByState(String stateCode);
	List<District> findDistrictOfState(String stateCode,String distCode);
	List<District> findDistrictListByStateToken(String stateCode, String nationality);
}
