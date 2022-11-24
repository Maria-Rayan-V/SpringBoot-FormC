 package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ivfrt.frt.frro.web.masters.model.MaritalStatus;
import ivfrt.frt.frro.web.masters.repositry.MaritalStatusRepositry;

@Repository
public class MaritalStatusServices implements MaritalStatusRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbc;
	
	
	@Override
	public List<MaritalStatus> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("SELECT marital_status_code,marital_status_desc from fr_marital_status"
				+ " where  active='Y' order by marital_status_desc",  (rs, rowNum) ->
                new MaritalStatus(
                        rs.getString("marital_status_code"),
                        rs.getString("marital_status_desc")
                       
                        )
                );
	}

	@Override
	public List<MaritalStatus> findByMaritalStatusCode(String mStatusCode) {
		// TODO Auto-generated method stub
		return jdbc.query("SELECT marital_status_code,marital_status_desc from fr_marital_status"
				+ " where marital_status_code=? order by marital_status_desc ", new Object[] {mStatusCode},
				(rs, rowNum)->new MaritalStatus(
                        rs.getString("marital_status_code"),
                        rs.getString("marital_status_desc")
                       
                        )
				
				);
	}

}
