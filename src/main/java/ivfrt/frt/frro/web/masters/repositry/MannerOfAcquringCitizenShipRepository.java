package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.MannerOfAcquringCitizenShip;

public interface MannerOfAcquringCitizenShipRepository {

	List<MannerOfAcquringCitizenShip> findAll();
	List<MannerOfAcquringCitizenShip> findByCode(String citizenShipCode);
	
}
