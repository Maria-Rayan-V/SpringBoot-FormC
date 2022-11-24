package ivfrt.frt.frro.web.masters.model;

public class Relation {
	private String relation_code;
	
	private String relation_desc;
	
	public Relation() {
		super();
	}
	
	
	
	public Relation(String relation_code, String relation_desc) {
		super();
		this.relation_code = relation_code;
		this.relation_desc = relation_desc;
	}
	@Override
	public String toString() {
		return "Relation [relation_code=" + relation_code + ", relation_desc=" + relation_desc + "]";
	}	
	
	public String getRelation_code() {
		return relation_code;
	}
	public void setRelation_code(String relation_code) {
		this.relation_code = relation_code;
	}
	public String getRelation_desc() {
		return relation_desc;
	}
	public void setRelation_desc(String relation_desc) {
		this.relation_desc = relation_desc;
	}
}
