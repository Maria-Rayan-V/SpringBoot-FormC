package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.VisitPurpose;
import ivfrt.frt.frro.web.masters.repositry.VisitPurposeRepository;

@Repository
public class VisitPurposeServices implements VisitPurposeRepository {

	@Autowired
	JdbcTemplate jdbctemplete;
	
	
	@Override
	public List<VisitPurpose> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select purpose_code,purpose_desc from fr_purpose "
				+ " where  active='Y' order by purpose_desc",  (rs, rowNum) ->
                new VisitPurpose(
                        rs.getString("purpose_code"),
                        rs.getString("purpose_desc")
                       
                        )
                );
	}

	@Override
	public List<VisitPurpose> findVisitPurposeByCode(String purposeCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select purpose_code,purpose_desc from fr_purpose "
				+ " where purpose_code=? order by purpose_desc ", new Object[] {purposeCode},
				(rs, rowNum)->new VisitPurpose(
                        rs.getString("purpose_code"),
                        rs.getString("purpose_desc")
                       
                        )
				
				);
	}

	

	
	
	
}
