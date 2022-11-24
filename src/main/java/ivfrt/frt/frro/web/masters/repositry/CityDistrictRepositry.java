package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.CityDistrict;

public interface CityDistrictRepositry {

	List<CityDistrict> findAll();
	List<CityDistrict> findByCityCode(String distCode);
	List<CityDistrict> findCityListByState(String stateCode);
}
