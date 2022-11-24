package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.District;
import ivfrt.frt.frro.web.masters.repositry.DistrictRepositry;

@Repository
public class DistrictServices implements DistrictRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbctemplete; 
	
	@Override
	public List<District> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT district_code,district_name,state_code from fr_districts where active='Y' order by district_name", 
				(rs,rowNum)->
		           new District(	
		        		 rs.getString("district_code"),
		  				 rs.getString("district_name"),
		  				 rs.getString("state_code")
		        		   		)
				
				);
	}



	@Override
	public List<District> findByDistrictCode(String distCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT district_code,district_name,state_code from fr_districts where district_code=? order by district_name",
				new Object[] {distCode},(rs,rowNum)->
		 new District(
				 rs.getString("district_code"),
				 rs.getString("district_name"),
				 rs.getString("state_code")
				 	)
		 );
	}



	@Override
	public List<District> findDistrictListByState(String stateCode) {
		// TODO Auto-generated method stub
		String q="SELECT city_district_code,city_district_name,state_code from city_district where state_code=?  and active='Y' order by city_district_name";
		//String q="SELECT district_code,district_name,state_code from fr_districts where state_code=?  and active='Y' order by district_name"
		return jdbctemplete.query(q,
				new Object[] {stateCode},(rs,rowNum)->
		 new District(
				 rs.getString("city_district_code"),
				 rs.getString("city_district_name"),
				 rs.getString("state_code")
				 	)
		 );
	}



	@Override
	public List<District> findDistrictListByStateToken(String stateCode, String nationality) {
		String Cities_query=" select distinct  c.city_district_code,c.city_district_name  from fr_frro_fro_city_district_mapping a ,fr_frro_fro_list b,"
		   		+ "city_district  c, fr_hub_spoke_list hsl where  a.active='Y' and b.nationality is null and  b.active ='Y' and b.reg_online_flag='Y'  and"
		   		+ " a.frro_fro_code=b.frro_fro_code and a.frro_fro_code not in (select hsl.frro_fro_code from fr_hub_spoke_list hsl) and b.frro_fro_code"
		   		+ " not in (select hsl.frro_fro_code from fr_hub_spoke_list hsl) and c.city_district_code=a.city_district_code and c.state_code=b.state_code"
		   		+ " and b.state_code=?"
		   		+ "union "
		   		+ "select distinct  c.city_district_code,c.city_district_name  from fr_frro_fro_city_district_mapping a ,"
		   		+ "fr_frro_fro_list b,city_district  c ,fr_frro_fro_nationality_mapping d, fr_hub_spoke_list hsl where b.nationality='N' "
		   		+ "and d.nationality=? and d.active='Y' and b.frro_fro_code=d.frro_fro_code and b.frro_fro_code"
		   		+ " not in (select hsl.frro_fro_code from fr_hub_spoke_list hsl) and d.frro_fro_code"
		   		+ " not in (select hsl.frro_fro_code from fr_hub_spoke_list hsl) and  a.active='Y' and "
		   		+ " b.active ='Y' and b.reg_online_flag='Y'  and a.frro_fro_code=b.frro_fro_code and a.frro_fro_code "
		   		+ "not in (select hsl.frro_fro_code from fr_hub_spoke_list hsl) and b.frro_fro_code "
		   		+ "not in (select hsl.frro_fro_code from fr_hub_spoke_list hsl) and c.city_district_code=a.city_district_code "
		   		+ "and c.state_code=b.state_code and b.state_code=? order by city_district_name ";

		return jdbctemplete.query(Cities_query,
				new Object[] {stateCode,nationality, stateCode},(rs,rowNum)->
		 new District(
				 rs.getString("city_district_code"),
				 rs.getString("city_district_name")
				 	)
		 );
	}



	@Override
	public List<District> findDistrictOfState(String stateCode, String distCode) {
		
		String query="SELECT district_code,district_name,state_code from fr_districts where state_code=? and  district_code=? order by district_name";
		
		return jdbctemplete.query(query,
				new Object[] {stateCode,distCode},(rs,rowNum)->
		 new District(
				 rs.getString("district_code"),
				 rs.getString("district_name"),
				 rs.getString("state_code")
				 	)
		 );
	}

}
