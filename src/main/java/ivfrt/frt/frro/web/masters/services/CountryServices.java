package ivfrt.frt.frro.web.masters.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Country;
import ivfrt.frt.frro.web.masters.repositry.CountryRepository;

@Repository
public class CountryServices implements CountryRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbctemplete;
	
	
	@Override
	public List<Country> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select COUNTRY_NAME,COUNTRY_CODE "
				+ " from fr_country "
				+ " where  active='Y' order by COUNTRY_NAME",  (rs, rowNum) ->
                new Country(
                        rs.getString("COUNTRY_CODE"),
                        rs.getString("COUNTRY_NAME")
                       
                        )
                );
	}

	@Override
	public List<Country> findCountryByCode(String countryCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select COUNTRY_NAME,COUNTRY_CODE from fr_country "
				+ " where COUNTRY_CODE=?  order by COUNTRY_NAME", new Object[] {countryCode},
				(rs, rowNum)->new Country(
						 rs.getString("COUNTRY_CODE"),
	                     rs.getString("COUNTRY_NAME")
						)
				
				);
	}

	
	
	//select COUNTRY_NAME,COUNTRY_CODE from fr_country where  active='Y' order by COUNTRY_NAME
	
}
