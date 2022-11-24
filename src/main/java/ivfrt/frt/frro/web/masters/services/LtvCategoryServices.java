package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.LtvCategory;
import ivfrt.frt.frro.web.masters.repositry.LtvCategoryRepositry;

@Repository
public class LtvCategoryServices implements LtvCategoryRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbctemplete; 
	
	@Override
	public List<LtvCategory> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT ltv_category_code,ltv_category_desc,nationality from "
				+ " fr_ltv_category where active='Y' order by ltv_category_desc", 
				(rs,rowNum)->
		           new LtvCategory(	
		        		   rs.getString(1),
		        		   rs.getString(2),
		        		   rs.getString(3)
		        		   		)
				
				);
	}

	@Override
	public List<LtvCategory> findByLTVCatCode(String icpsSrNo) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT ltv_category_code,ltv_category_desc,nationality from fr_ltv_category where ltv_category_code=?",
				new Object[] {icpsSrNo},(rs,rowNum)->
		 new LtvCategory(
				 	rs.getString(1),
      		   		rs.getString(2),
      		   		rs.getString(3)
				 	)
		 );
	}

	@Override
	public List<LtvCategory> findLtvCatListByNationality(String ltvCatNationality) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT ltv_category_code,ltv_category_desc,nationality "
				+ " from fr_ltv_category where nationality=? order by ltv_category_desc ",
				new Object[] {ltvCatNationality},(rs,rowNum)->
		 new LtvCategory(
				 	rs.getString(1),
      		   		rs.getString(2),
      		   		rs.getString(3)
				 	)
		 );
	}

}
