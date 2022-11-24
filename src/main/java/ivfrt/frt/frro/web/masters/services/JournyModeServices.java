 package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.JournyMode;
import ivfrt.frt.frro.web.masters.model.MaritalStatus;
import ivfrt.frt.frro.web.masters.repositry.JournyModeRepositry;
import ivfrt.frt.frro.web.masters.repositry.MaritalStatusRepositry;

@Repository
public class JournyModeServices implements JournyModeRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbc;
	
	
	@Override
	public List<JournyMode> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("SELECT mode_code,mode_desc from fr_mode_of_journey"
				+ " where  active='Y' order by mode_desc",  (rs, rowNum) ->
                new JournyMode(
                        rs.getString("mode_code"),
                        rs.getString("mode_desc")
                       
                        )
                );
	}

	@Override
	public List<JournyMode> findModeDescByCode(String journyModeCode) {
		// TODO Auto-generated method stub
		return jdbc.query("SELECT mode_code,mode_desc from fr_mode_of_journey"
				+ " where mode_code=? order by mode_desc", new Object[] {journyModeCode},
				(rs, rowNum)->new JournyMode(
                        rs.getString("mode_code"),
                        rs.getString("mode_desc")
                       
                        )
				
				);
	}

	

}
