package ivfrt.frt.frro.web.formc.arrivaldeparture;

import java.util.List;

import ivfrt.frt.frro.web.formc.FormC;

public interface ArrivalDepartureDetailsRepository {

	DepartureDetails insertDepartDetail(DepartureDetails departdetail);

	List<ArrivalDetail> findArrivalDetail(ArrivalDetail arrivalDetail);
}
