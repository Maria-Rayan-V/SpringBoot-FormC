package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Relation;
import ivfrt.frt.frro.web.masters.repositry.RelationRepository;
@Repository
public class RelationServices implements RelationRepository{


	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbc;
	
	@Override
	public List<Relation> findAll() {

		return jdbc.query("SELECT fr_relation_code, fr_relation_desc, entered_by, entered_on, active, inactive_date  FROM fr_relation where active='Y' order by fr_relation_desc",  (rs, rowNum) ->
                new Relation(
                        rs.getString("fr_relation_code"),
                        rs.getString("fr_relation_desc")
                       
                        )
                );
	
	}

	@Override
	public List<Relation> findRelationByCode(String relation_code) {
	
		String query="SELECT fr_relation_code, fr_relation_desc,"
				+ "  entered_by, entered_on, active, inactive_date  FROM fr_relation where active='Y' "
				+ " and fr_relation_code=? order by fr_relation_desc";
		
		
		return jdbc.query(query, new Object[] {Integer.parseInt(relation_code)},
				(rs, rowNum)->new Relation(
						 rs.getString("fr_relation_code"),
	                     rs.getString("fr_relation_desc")
						)
				
				);
	}


}
