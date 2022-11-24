package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Gender;

public interface GenderRepositry {

	List<Gender> findAll();
	List<Gender> findGenderByCode(String genderCode);
}
