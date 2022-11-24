package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.State;

public interface StateRepositry {

	List<State> findAll();
	List<State> findByStateCode(String stateCode);
}
