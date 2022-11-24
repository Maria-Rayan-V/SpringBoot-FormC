 package ivfrt.frt.frro.web.masters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.VisaEntries;
import ivfrt.frt.frro.web.masters.repositry.VisaEntriesRepositry;

@Repository
public class VisaEntryServices implements VisaEntriesRepositry {

	@Autowired
	@Qualifier("jdbcEservices")
	private JdbcTemplate jdbc;
	
	
	@Override
	public List<VisaEntries> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("SELECT entry_type_code,entry_type_name from fr_no_of_entries"
				+ " where  active='Y' order by entry_type_code asc",  (rs, rowNum) ->
                new VisaEntries(
                        rs.getString("entry_type_code"),
                        rs.getString("entry_type_name")
                       
                        )
                );
	}

	@Override
	public List<VisaEntries> findEntryTypeNameByCode(String vEntryTypeCode) {
		// TODO Auto-generated method stub
		return jdbc.query("SELECT entry_type_code,entry_type_name from fr_no_of_entries"
				+ " where entry_type_code=? order by entry_type_code asc", new Object[] {vEntryTypeCode},
				(rs, rowNum)->new VisaEntries(
                        rs.getString("entry_type_code"),
                        rs.getString("entry_type_name")
                       
                        )
				
				);
	}

}
