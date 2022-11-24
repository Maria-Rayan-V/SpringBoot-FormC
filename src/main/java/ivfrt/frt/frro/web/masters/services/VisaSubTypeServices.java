package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.VisaSubType;
import ivfrt.frt.frro.web.masters.repositry.VisaSubTypeRepository;

@Repository
public class VisaSubTypeServices implements VisaSubTypeRepository {

	@Autowired
    @Qualifier("jdbcEservices")
	JdbcTemplate jdbctemplete;
	@Override
	public List<VisaSubType> findAll() {
		// TODO Auto-generated method stub  String visaType, String visaSubType, String purpose, String purposeCode
		return jdbctemplete.query("select visa_type_code,visa_sub_type,purpose,purposecode from fr_visa_purpose where active='Y'", 
				(rs,rowNum)->
		           new VisaSubType(	
		        		   rs.getString("visa_type_code"),
		        		   rs.getString("visa_sub_type"),
		        		   rs.getString("purpose"),
		        		   rs.getString("purposecode")
		        		   		)
				
				);
	}

	@Override
	public List<VisaSubType> findVisaSubTypeByVisaType(String visaType) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select visa_type_code,visa_sub_type,purpose,purposecode from fr_visa_purpose where active='Y' and  visa_type_code=? order by visa_sub_type",
				new Object[] {visaType},(rs,rowNum)->
		 new VisaSubType(	
      		   rs.getString("visa_type_code"),
      		   rs.getString("visa_sub_type"),
      		   rs.getString("purpose"),
      		   rs.getString("purposecode")
      		   		)
		 );
	}

	@Override
	public List<VisaSubType> fetchPurposeByPurposeCode(String purposeCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select visa_type_code,visa_sub_type,purpose,purposecode from fr_visa_purpose where active='Y' and  purposecode=? order by visa_sub_type",
				new Object[] {purposeCode},(rs,rowNum)->
		 new VisaSubType(	
      		   rs.getString("visa_type_code"),
      		   rs.getString("visa_sub_type"),
      		   rs.getString("purpose"),
      		   rs.getString("purposecode")
      		   		)
		 );
	}

}
