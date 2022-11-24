package ivfrt.frt.frro.web.user.frroservicelist;

import java.util.List;
import java.util.Map;

public interface FrroListRepository {

	
	Map<String, Object> fetchFrroListByStateAndCityDist(String nationality,String state,String cityDist );
}
