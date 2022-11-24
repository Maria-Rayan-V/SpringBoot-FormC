package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.Icps;
import ivfrt.frt.frro.web.masters.repositry.IcpsRepositry;

@Repository
public class IcpsServices implements IcpsRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbctemplete; 
	
	@Override
	public List<Icps> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT icp_srno,icp_desc,icp_type_code from fr_icps where active='Y' order by icp_desc ", 
				(rs,rowNum)->
		           new Icps(	
		        		   rs.getString(1),
		        		   rs.getString(2),
		        		   rs.getString(3)
		        		   		)
				
				);
	}

	@Override
	public List<Icps> findByStateCode(String icpsSrNo) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT icp_srno,icp_desc,icp_type_code from fr_icps where icp_srno=? order by icp_desc",
				new Object[] {icpsSrNo},(rs,rowNum)->
		 new Icps(
				 	rs.getString(1),
      		   		rs.getString(2),
      		   		rs.getString(3)
				 	)
		 );
	}

	@Override
	public List<Icps> findIcpsByType(String icpTypeCode) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT icp_srno,icp_desc,icp_type_code from fr_icps where icp_type_code=? and   active='Y' order by icp_desc",
				new Object[] {icpTypeCode},(rs,rowNum)->
		 new Icps(
				 	rs.getString(1),
      		   		rs.getString(2),
      		   		rs.getString(3)
				 	)
		 );
	}

	@Override
	public List<Icps> findAllIcps() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("SELECT icp_srno,icp_desc,icp_type_code from fr_icps where  active='Y' order by icp_desc",
				(rs,rowNum)->
		 new Icps(
				 	rs.getString(1),
      		   		rs.getString(2),
      		   		rs.getString(3)
				 	)
		 );
	}

}
