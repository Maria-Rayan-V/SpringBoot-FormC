package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.SplCategory;
import ivfrt.frt.frro.web.masters.repositry.SplCategoryRepository;

@Repository
public class SplCategoryServices implements SplCategoryRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbctemplete;
	
	
	@Override
	public List<SplCategory> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select spl_category_code,spl_category_desc from fr_formc_spl_category "
				+ " where  active='Y' order by spl_category_desc",  (rs, rowNum) ->
                new SplCategory(
                        rs.getString("spl_category_code"),
                        rs.getString("spl_category_desc")
                       
                        )
                );
	}

	

	@Override
	public List<SplCategory> findSplCatByCode(String splCatCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select spl_category_code,spl_category_desc from fr_formc_spl_category "
				+ " where spl_category_code=? order by spl_category_desc", new Object[] {splCatCode},
				(rs, rowNum)->new SplCategory(
                        rs.getString("spl_category_code"),
                        rs.getString("spl_category_desc")
                       
                        )
				
				);
	}
}
