package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Occupation;

public interface OccupationRepository {

	List<Occupation> findAll();
	List<Occupation> findOccupationByCode(String occupation_code);
}
