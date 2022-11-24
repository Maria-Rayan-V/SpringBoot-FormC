package ivfrt.frt.frro.web.masters.model;

public class Category {

	private String  category_code;
	private String category_desc;
	
	
	public Category() {
		super();
	}
	public Category(String category_code, String category_desc) {
		super();
		this.category_code = category_code;
		this.category_desc = category_desc;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
	
	
}
