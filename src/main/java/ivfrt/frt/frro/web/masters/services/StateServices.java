package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.State;
import ivfrt.frt.frro.web.masters.repositry.StateRepositry;

@Repository
public class StateServices implements StateRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbctemplete; 
	
	@Override
	public List<State> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT state_code,state_name from fr_states where active='Y' order by state_name ", 
				(rs,rowNum)->
		           new State(	
		        		   rs.getString("state_code"),
		        		   rs.getString("state_name")
		        		   		)
				
				);
	}

	@Override
	public List<State> findByStateCode(String stateCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT state_code,state_name from fr_states where state_code=? order by state_name",
				new Object[] {stateCode},(rs,rowNum)->
		 new State(
				 rs.getString("state_code"),
				 rs.getString("state_name")
				 	)
		 );
	}

}
