package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Category;

public interface CategoryRepository {

	List<Category> findAll();
	List<Category> findCategory(String userCatCode);
	List<Category> findCategoryDescByCode(String catCode);
	List<Category> findCategoryVisaType(String catCode,String visaType);

}
