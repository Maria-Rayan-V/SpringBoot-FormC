package ivfrt.frt.frro.web.masters.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Country;
import ivfrt.frt.frro.web.masters.model.VisaType;
import ivfrt.frt.frro.web.masters.repositry.CountryRepository;
import ivfrt.frt.frro.web.masters.repositry.VisaTypeRepository;

@Repository
public class VisaTypeServices implements VisaTypeRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbctemplete;
	
	
	@Override
	public List<VisaType> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT visatype_id,visatype_desc from fr_visa_type "
				+ " where  active='Y' order by visatype_desc",  (rs, rowNum) ->
                new VisaType(
                        rs.getString("visatype_id"),
                        rs.getString("visatype_desc")
                       
                        )
                );
	}



	@Override
	public List<VisaType> findVisaTypeByCode(String visaTypeCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT visatype_id,visatype_desc from fr_visa_type "
				+ " where visatype_id=? order by visatype_desc ", new Object[] {visaTypeCode},
				(rs, rowNum)->new VisaType(
						 rs.getString("visatype_id"),
	                     rs.getString("visatype_desc")
						)
				
				);
	}

	
	
}
