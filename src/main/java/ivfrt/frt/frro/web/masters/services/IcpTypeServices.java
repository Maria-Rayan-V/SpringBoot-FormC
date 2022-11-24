package ivfrt.frt.frro.web.masters.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.IcpType;
import ivfrt.frt.frro.web.masters.repositry.IcpTypeRepository;

@Repository
public class IcpTypeServices implements IcpTypeRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbctemplete;
	
	
	@Override
	public List<IcpType> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT icp_type,icp_type_desc from fr_icp_type "
				+ " where  active='Y' order by icp_type_desc",  (rs, rowNum) ->
                new IcpType(
                        rs.getString("icp_type"),
                        rs.getString("icp_type_desc")
                       
                        )
                );
	}

	@Override
	public List<IcpType> findGenderByCode(String countryCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT icp_type,icp_type_desc from fr_icp_type "
				+ " where icp_type=? order by icp_type_desc", new Object[] {countryCode},
				(rs, rowNum)->new IcpType(
						 rs.getString("icp_type"),
	                     rs.getString("icp_type_desc")
						)
				
				);
	}

	
	
	
}
