package ivfrt.frt.frro.web.masters.model;

public class State {

	private String stateCode;
	private String stateName;
	
	
	public State(String stateCode, String ststeName) {
		super();
		this.stateCode = stateCode;
		this.stateName = ststeName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStsteName() {
		return stateName;
	}
	public void setStsteName(String ststeName) {
		this.stateName = ststeName;
	}
	
	
}
