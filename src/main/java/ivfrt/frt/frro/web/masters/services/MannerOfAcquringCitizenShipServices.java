package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ivfrt.frt.frro.web.masters.model.MannerOfAcquringCitizenShip;
import ivfrt.frt.frro.web.masters.repositry.MannerOfAcquringCitizenShipRepository;

@Repository
public class MannerOfAcquringCitizenShipServices implements MannerOfAcquringCitizenShipRepository{
	@Autowired
	@Qualifier("jdbcEservices")	
	JdbcTemplate jdbc;
	
	@Override
	public List<MannerOfAcquringCitizenShip> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("select citizenship_code,citizenship_desc from fr_citizenship_type where active='Y' order by citizenship_desc",  (rs, rowNum) ->
                new MannerOfAcquringCitizenShip(
                        rs.getString("citizenship_code"),
                        rs.getString("citizenship_desc")
                       
                        )
                );
	}

	@Override
	public List<MannerOfAcquringCitizenShip> findByCode(String citizenShipCode) {
		// TODO Auto-generated method stub
		return jdbc.query("select citizenship_code,citizenship_desc from fr_citizenship_type where citizenship_code=?  order by citizenship_desc", new Object[] {citizenShipCode},
				(rs, rowNum)->new MannerOfAcquringCitizenShip(
                        rs.getString("citizenship_code"),
                        rs.getString("citizenship_desc")
                       
                        )
				
				);
	}

}
