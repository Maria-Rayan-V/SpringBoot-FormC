package ivfrt.frt.frro.web.masters.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ivfrt.frt.frro.web.masters.model.DocType;
import ivfrt.frt.frro.web.masters.repositry.DocTypeRepository;

@Repository
public class DocTypeServices implements DocTypeRepository {

	@Autowired
	@Qualifier("jdbcEservices")
	JdbcTemplate jdbctemplete;
	
	
	@Override
	public List<DocType> findAll() {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select doc_type_code,doc_type_name from fr_doc_type "
				+ " where  active='Y' order by doc_type_name",  (rs, rowNum) ->
                new DocType(
                        rs.getString("doc_type_code"),
                        rs.getString("doc_type_name")
                       
                        )
                );
	}

	@Override
	public List<DocType> findDocTypeByCode(String doc_type_code) {
		// TODO Auto-generated method stub
		return jdbctemplete.query("select doc_type_code,doc_type_name from fr_doc_type "
				+ " where doc_type_code=? order by doc_type_name ", new Object[] {doc_type_code},
				(rs, rowNum)->new DocType(
						 rs.getString("doc_type_code"),
	                     rs.getString("doc_type_name")
						)
				
				);
	}

	
	
	
}
