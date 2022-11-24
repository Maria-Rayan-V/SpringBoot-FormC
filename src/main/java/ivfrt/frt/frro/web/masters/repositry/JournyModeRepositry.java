package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.JournyMode;

public interface JournyModeRepositry {

	List<JournyMode> findAll();
	List<JournyMode> findModeDescByCode(String journyModeCode);
}
