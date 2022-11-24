package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.LtvCategory;

public interface LtvCategoryRepositry {

	List<LtvCategory> findAll();
	List<LtvCategory> findByLTVCatCode(String ltvCatCode);
	List<LtvCategory> findLtvCatListByNationality(String ltvCatNationality);
	
}
