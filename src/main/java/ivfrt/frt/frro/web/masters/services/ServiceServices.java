package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Service;
import ivfrt.frt.frro.web.masters.repositry.ServiceRepository;
import ivfrt.frt.frro.web.masters.repositry.ServiceRepositry;

@Repository
public class ServiceServices implements ServiceRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbctemplete;
	
	
	@Override
	public List<Service> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT form_type_code,form_type_desc FROM fr_online_form_type order by form_type_desc",  (rs, rowNum) ->
                new Service(
                        rs.getString("form_type_code"),
                        rs.getString("form_type_desc")
                       
                        )
                );
	}

	

	@Override
	public List<Service> findServiceByCode(String serviceCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT form_type_code,form_type_desc FROM fr_online_form_type "
				+ " where form_type_code=?  order by form_type_desc", new Object[] {serviceCode},
				(rs, rowNum)->new Service(
						 rs.getString("form_type_code"),
	                     rs.getString("form_type_desc")
						)
				
				);
	}



	@Override
	public List<Service> findAllForMobile() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT form_type_code,form_type_desc FROM fr_online_form_type where mobile_flag='M' "
				+ " order by form_type_desc",  (rs, rowNum) ->
                new Service(
                        rs.getString("form_type_code"),
                        rs.getString("form_type_desc")
                       
                        )
                );
	}



	

	
	
	
}
