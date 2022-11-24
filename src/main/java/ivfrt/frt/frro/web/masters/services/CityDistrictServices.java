package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.CityDistrict;
import ivfrt.frt.frro.web.masters.model.District;
import ivfrt.frt.frro.web.masters.repositry.CityDistrictRepositry;
import ivfrt.frt.frro.web.masters.repositry.DistrictRepositry;

@Repository
public class CityDistrictServices implements CityDistrictRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbctemplete; 
	
	@Override
	public List<CityDistrict> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT city_code,city_name,state_code from fr_cities where active='Y' order by city_name", 
				(rs,rowNum)->
		           new CityDistrict(	
		        		   rs.getString("city_code"),
		        		   rs.getString("city_name"),
		        		   rs.getString("state_code")
		        		   		)
				
				);
	}



	@Override
	public List<CityDistrict> findByCityCode(String distCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT city_code,city_name,state_code from fr_cities where city_code=? order by city_name",
				new Object[] {distCode},(rs,rowNum)->
		 new CityDistrict(	
			   rs.getString("city_code"),
      		   rs.getString("city_name"),
      		   rs.getString("state_code"))
		 );
	}



	@Override
	public List<CityDistrict> findCityListByState(String stateCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT city_code,city_name,state_code from fr_cities where state_code=? and active='Y' order by city_name",
				new Object[] {stateCode},(rs,rowNum)->
		 new CityDistrict(
				   rs.getString("city_code"),
	      		   rs.getString("city_name").trim(),
	      		   rs.getString("state_code")
				 	)
		 );
	}

}
