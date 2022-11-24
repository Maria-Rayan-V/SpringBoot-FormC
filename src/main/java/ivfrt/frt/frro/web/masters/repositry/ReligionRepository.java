package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Religion;

public interface ReligionRepository {
	List<Religion> findAll();
	List<Religion> findReligionByCode(String religion_code);
}
