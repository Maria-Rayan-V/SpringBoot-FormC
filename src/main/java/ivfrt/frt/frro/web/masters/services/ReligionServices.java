package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Religion;
import ivfrt.frt.frro.web.masters.repositry.ReligionRepository;
@Repository
public class ReligionServices implements ReligionRepository{


	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbc;
	
	@Override
	public List<Religion> findAll() {

		return jdbc.query("SELECT religion_code, religion_desc, entered_by, entered_on, active, inactive_date  FROM fr_religion where active='Y' order by religion_desc",  (rs, rowNum) ->
                new Religion(
                        rs.getString("religion_code"),
                        rs.getString("religion_desc")
                       
                        )
                );
	
	}

	@Override
	public List<Religion> findReligionByCode(String religion_code) {
	
		String query="SELECT religion_code, religion_desc,"
				+ "  entered_by, entered_on, active, inactive_date  FROM fr_religion where active='Y' "
				+ " and religion_code=? order by religion_desc";
		
		
		return jdbc.query(query, new Object[] {religion_code},
				(rs, rowNum)->new Religion(
						 rs.getString("religion_code"),
	                     rs.getString("religion_desc")
						)
				
				);
	}


}
