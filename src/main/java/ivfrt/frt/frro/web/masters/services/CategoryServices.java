package ivfrt.frt.frro.web.masters.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Category;
import ivfrt.frt.frro.web.masters.repositry.CategoryRepository;

@Repository
public class CategoryServices implements CategoryRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbcEservices;
	
	@Override
	public List<Category> findAll() {
	
		String query = "select category_code,category_desc from fr_category where active='Y' order by category_desc";
		
		return jdbcEservices.query(query, 
				(rs,rowNum)->
		           new Category(	
		        		   rs.getString("category_code"),
		        		   rs.getString("category_desc")
		        		   		)
				
				);
	}

	@Override
	public List<Category> findCategory(String userCatCode) {
		
		String category_query="";
		
		if(userCatCode!=null && userCatCode.equals("1"))
		{
	         category_query = "select category_code,category_desc from fr_category where active='Y' and category_code='1' order by category_desc";
		}
		else  {
			 category_query = "select category_code,category_desc from fr_category where active='Y' and category_code<>'1'  order by category_desc";
		}

		return jdbcEservices.query(category_query, 
				(rs,rowNum)->
		           new Category(	
		        		   rs.getString("category_code"),
		        		   rs.getString("category_desc")
		        		   		)
				
				);
		
	}

	@Override
	public List<Category> findCategoryDescByCode(String catCode) {
		
		
		String query = "select category_code,category_desc from fr_category where category_code=?";
		
		return jdbcEservices.query(query,new Object[] {catCode}, 
				(rs,rowNum)->
		           new Category(	
		        		   rs.getString("category_code"),
		        		   rs.getString("category_desc")
		        		   		)
				
				);
	}

	@Override
	public List<Category> findCategoryVisaType(String userCatCode, String visaType) {
		
		String category_query="";
		List<Category> catList=new ArrayList<Category>();

			if(userCatCode.equals("1")){
				
		         category_query = "select category_code,category_desc from fr_category where active='Y' "
		         		+ " and category_code='1' order by category_desc";
			}
			else if(visaType.equals("11"))
			{
				 category_query = "select category_code,category_desc from fr_category where active='Y' "
				 		+ " and category_code<>'1' and category_code !='B'  order by category_desc";
			} else {

				 category_query = "select category_code,category_desc from fr_category where active='Y' "
				 		+ " and category_code<>'1' and category_code='B'  order by category_desc";
			
			}
            
			catList= jdbcEservices.query(category_query, 
					(rs,rowNum)->
			           new Category(	
			        		   rs.getString("category_code"),
			        		   rs.getString("category_desc")
			        		   		)
					
					);
		
		
		
		return catList;
	}

}
