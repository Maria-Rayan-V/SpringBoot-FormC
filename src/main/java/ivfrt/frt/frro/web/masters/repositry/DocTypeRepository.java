package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.DocType;

public interface DocTypeRepository {

	List<DocType> findAll();
	List<DocType> findDocTypeByCode(String doc_type_code);
}
