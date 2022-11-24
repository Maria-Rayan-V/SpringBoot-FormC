package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Country;

public interface CountryRepository {

	List<Country> findAll();
	List<Country> findCountryByCode(String countryCode);
}
