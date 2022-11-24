package ivfrt.frt.frro.web.masters.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Gender;
import ivfrt.frt.frro.web.masters.repositry.GenderRepositry;

@Repository
public class GenderServices implements GenderRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbctemplete;
	
	
	@Override
	public List<Gender> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select gender_code,gender_desc from fr_gender "
				+ " where  active='Y' order by gender_desc",  (rs, rowNum) ->
                new Gender(
                        rs.getString("gender_code"),
                        rs.getString("gender_desc")
                       
                        )
                );
	}

	@Override
	public List<Gender> findGenderByCode(String countryCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select gender_code,gender_desc from fr_gender "
				+ " where gender_code=? order by gender_desc ", new Object[] {countryCode},
				(rs, rowNum)->new Gender(
						 rs.getString("gender_code"),
	                     rs.getString("gender_desc")
						)
				
				);
	}

	
	
	
}
