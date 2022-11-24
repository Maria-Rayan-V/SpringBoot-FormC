package ivfrt.frt.frro.web.user.frroservicelist;

import java.util.List;


public interface FrroListAppRepository {

	List<FrroListApp> fetchFrroListByStateAndCityDistAPP(String nationality,String state,String cityDist );
}
