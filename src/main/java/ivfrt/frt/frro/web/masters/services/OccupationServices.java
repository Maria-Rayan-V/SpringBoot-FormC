package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Occupation;
import ivfrt.frt.frro.web.masters.repositry.OccupationRepository;

@Repository
public class OccupationServices implements OccupationRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbc;
	
	@Override
	public List<Occupation> findAll() {

		return jdbc.query("SELECT occupation_code, occupation_desc, entered_by, entered_on, active, inactive_date  FROM fr_occupation where active='Y' order by occupation_desc",  (rs, rowNum) ->
                new Occupation(
                        rs.getString("occupation_code"),
                        rs.getString("occupation_desc")
                       
                        )
                );
	
	}

	@Override
	public List<Occupation> findOccupationByCode(String occupation_code) {
	
		String query="SELECT occupation_code, occupation_desc,"
				+ "  entered_by, entered_on, active, inactive_date  FROM fr_occupation where active='Y' "
				+ " and occupation_code=? order by occupation_desc";
		
		
		return jdbc.query(query, new Object[] {occupation_code},
				(rs, rowNum)->new Occupation(
						 rs.getString("occupation_code"),
	                     rs.getString("occupation_desc")
						)
				
				);
	}

}
