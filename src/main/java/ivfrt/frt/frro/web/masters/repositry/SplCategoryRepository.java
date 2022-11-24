package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.SplCategory;

public interface SplCategoryRepository {

	List<SplCategory> findAll();
	List<SplCategory> findSplCatByCode(String splCatCode);
}
